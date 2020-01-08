package sample.UI;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MotionUI {
    public static void setUI() {
        Stage stage = new Stage();
        stage.setTitle("Motion Profile");
        stage.setWidth(MainUI.screenWidth);
        stage.setHeight(MainUI.screenHeight);

        MotionGraph.initializeGraph();

        Scene scene = new Scene(MotionGraph.getGraph(), stage.getWidth(), stage.getHeight());

        stage.setScene(scene);
        stage.show();
    }
}