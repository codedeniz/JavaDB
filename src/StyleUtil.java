import javafx.scene.Scene;

public class StyleUtil {
    public static void applyStyle(Scene scene) {
        // Add the CSS stylesheet
        scene.getStylesheets().add(StyleUtil.class.getResource("/style.css").toExternalForm());
    }
}
