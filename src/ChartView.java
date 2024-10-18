import javafx.scene.layout.BorderPane;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.geometry.Pos;
import javafx.util.StringConverter;
import javafx.scene.Scene;

import java.sql.ResultSet;

public class ChartView extends BorderPane {

    public ChartView() {
        BarChart<String, Number> barChart = createChart();
        setCenter(barChart);

      
        Button tableViewButton = new Button("Show Table View");
        tableViewButton.setOnAction(e -> {
            TableViewScene tableViewScene = new TableViewScene(); 
            Scene currentScene = getScene();
            if (currentScene != null) {
                currentScene.setRoot(tableViewScene); 
        });
        setBottom(tableViewButton); 
    }

    private BarChart<String, Number> createChart() {
        // Create axes
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        
        yAxis.setTickUnit(100);
        yAxis.setAutoRanging(true);
        yAxis.setLabel("Exchange Rate");

        yAxis.setTickLabelFormatter(new StringConverter<Number>() {
            @Override
            public String toString(Number number) {
                return String.format("%.2f", number.doubleValue()); 
            }

            @Override
            public Number fromString(String string) {
                return Double.parseDouble(string);
            }
        });

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Currency Exchange Rates");

        
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Exchange Rates"); 

     
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

      
        for (XYChart.Data<String, Number> data : series.getData()) {
            data.nodeProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                   
                    Label label = new Label(String.format("%.2f", data.getYValue().doubleValue()));
                    label.setTextFill(Color.WHITE);
                    label.setStyle("-fx-font-size: 12px; -fx-background-color: rgba(0, 0, 0, 0.5);"); 

                
                    StackPane bar = (StackPane) data.getNode();
                    StackPane.setAlignment(label, Pos.BOTTOM_CENTER);
                    bar.getChildren().add(label);
                }
            });
        }

        barChart.getData().add(series);
        return barChart;
    }
}
