\import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class CurrencyExchangeApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Currency Exchange Rates");

        primaryStage.getIcons().add(new Image("app_icon.png"));

        ChartView chartView = new ChartView();

        Scene scene = new Scene(chartView, 800, 600);

        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
