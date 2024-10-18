import javafx.scene.Scene;

public class StyleUtil {
    public static void applyStyle(Scene scene) {
       
        scene.getStylesheets().add(StyleUtil.class.getResource("/style.css").toExternalForm());
    }
}
