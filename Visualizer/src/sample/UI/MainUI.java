package sample.UI;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainUI {
    public static void setUI(Stage stage) {
        stage.setTitle("Pathfinding Visualizer");
        stage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
        stage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());

        double graphHeight = 0.80 * stage.getHeight();
        double graphWidth = 2 * graphHeight;

        Graph.initializeGraph(graphWidth, graphHeight);
        DataEntry.initializeDataEntry();

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10, 0, 0, 0));
        vBox.getChildren().add(Graph.getUIElement());
        vBox.getChildren().add(DataEntry.getUIElement());

        Scene scene = new Scene(vBox, stage.getWidth(), stage.getHeight());
        scene.getStylesheets().add("Styling/style.css");

        stage.setScene(scene);
        stage.show();
    }
}