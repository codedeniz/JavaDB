import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class CurrencyExchangeApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Currency Exchange Rates");

        // Set a unique icon for the application window
        primaryStage.getIcons().add(new Image("app_icon.png")); // Ensure this path is correct

        // Create an instance of your ChartView
        ChartView chartView = new ChartView();

        // Create a scene with the ChartView
        Scene scene = new Scene(chartView, 800, 600);

        // Link the CSS file to the scene (ensure the CSS file is in the right directory)
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        // Set the scene to the primary stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}