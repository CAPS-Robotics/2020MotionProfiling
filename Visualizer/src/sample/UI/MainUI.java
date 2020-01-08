package sample.UI;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainUI {
    public static double screenWidth;
    public static double screenHeight;

    public static void setUI(Stage stage) {
        screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
        screenHeight = Screen.getPrimary().getVisualBounds().getHeight();

        stage.setTitle("Pathfinding Visualizer");
        stage.setWidth(screenWidth);
        stage.setHeight(screenHeight);

        PathGraph.initializeGraph();
        DataEntry.initializeDataEntry();

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10, 0, 0, 0));
        vBox.getChildren().add(PathGraph.getUIElement());
        vBox.getChildren().add(DataEntry.getUIElement());

        Scene scene = new Scene(vBox, stage.getWidth(), stage.getHeight());
        scene.getStylesheets().add("Styling/style.css");

        stage.setScene(scene);
        stage.show();
    }
}