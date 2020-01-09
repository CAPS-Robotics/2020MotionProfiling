package sample.UI;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class MotionUI {
    public static void setUI() {
        Stage stage = new Stage();
        stage.setTitle("Motion Profile");
        stage.setWidth(MainUI.screenWidth);
        stage.setHeight(MainUI.screenHeight);

        MotionGraph.initializeGraph();

        Scene scene = new Scene(MotionGraph.getUIElement(), stage.getWidth(), stage.getHeight());

        stage.setScene(scene);
        stage.show();
    }
}