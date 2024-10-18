import javafx.scene.layout.BorderPane;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.control.Button; // Import Button
import javafx.scene.layout.StackPane;
import javafx.geometry.Pos;
import javafx.util.StringConverter;
import javafx.scene.Scene; // Import Scene

import java.sql.ResultSet;

public class ChartView extends BorderPane {

    public ChartView() {
        BarChart<String, Number> barChart = createChart();
        setCenter(barChart);

        // Create a button for switching to the table view
        Button tableViewButton = new Button("Show Table View");
        tableViewButton.setOnAction(e -> {
            TableViewScene tableViewScene = new TableViewScene(); // Create a new TableViewScene
            Scene currentScene = getScene();
            if (currentScene != null) {
                currentScene.setRoot(tableViewScene); // Switch to TableViewScene
            }
        });
        setBottom(tableViewButton); // Add the button to the bottom of the BorderPane
    }

    private BarChart<String, Number> createChart() {
        // Create axes
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        // Set y-axis properties
        yAxis.setTickUnit(100); // Adjust this value as necessary for readability
        yAxis.setAutoRanging(true);
        yAxis.setLabel("Exchange Rate");

        // Custom number formatting for y-axis
        yAxis.setTickLabelFormatter(new StringConverter<Number>() {
            @Override
            public String toString(Number number) {
                return String.format("%.2f", number.doubleValue()); // Format to 2 decimal places
            }

            @Override
            public Number fromString(String string) {
                return Double.parseDouble(string);
            }
        });

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Currency Exchange Rates");

        // Set up the series
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Exchange Rates");  // Optional: set a name for the series

        // Fetch data from the database and populate the series
        try {
            ResultSet rs = DBUtility.getExchangeRates();
            while (rs.next()) {
                String currency = rs.getString("currency_code");
                double rate = rs.getDouble("exchange_rate");
                series.getData().add(new XYChart.Data<>(currency, rate));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Add data labels to each bar
        for (XYChart.Data<String, Number> data : series.getData()) {
            data.nodeProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    // Create a text label for each bar
                    Label label = new Label(String.format("%.2f", data.getYValue().doubleValue()));
                    label.setTextFill(Color.WHITE);
                    label.setStyle("-fx-font-size: 12px; -fx-background-color: rgba(0, 0, 0, 0.5);"); // Optional styling

                    // Position the label above the bar
                    StackPane bar = (StackPane) data.getNode();
                    StackPane.setAlignment(label, Pos.BOTTOM_CENTER);
                    bar.getChildren().add(label);
                }
            });
        }

        // Add the series to the bar chart
        barChart.getData().add(series);
        return barChart;
    }
}
