import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;

import java.sql.ResultSet;

public class TableViewScene extends BorderPane {

    public TableViewScene() {
        TableView<ExchangeRate> tableView = new TableView<>();
        ObservableList<ExchangeRate> exchangeRates = FXCollections.observableArrayList();

     
        TableColumn<ExchangeRate, String> currencyColumn = new TableColumn<>("Currency");
        currencyColumn.setCellValueFactory(new PropertyValueFactory<>("currencyCode"));

       
        TableColumn<ExchangeRate, Double> rateColumn = new TableColumn<>("Exchange Rate");
        rateColumn.setCellValueFactory(new PropertyValueFactory<>("exchangeRate"));

    
        tableView.getColumns().addAll(currencyColumn, rateColumn);
        tableView.setItems(exchangeRates);

        try {
            ResultSet rs = DBUtility.getExchangeRates();
            while (rs.next()) {
                String currency = rs.getString("currency_code");
                double rate = rs.getDouble("exchange_rate");
                exchangeRates.add(new ExchangeRate(currency, rate));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

       
        setCenter(tableView);

      
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            ChartView chartView = new ChartView(); 
            Scene currentScene = getScene();
            if (currentScene != null) {
                currentScene.setRoot(chartView);
            }
        });

   
        setBottom(backButton);
    }
}
