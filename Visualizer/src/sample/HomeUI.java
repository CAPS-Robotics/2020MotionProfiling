package sample;

import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class HomeUI {
    static LineChart<Number, Number> graph;

    public static void setUI(Stage stage) {
        stage.setTitle("Pathfinding Visualizer");
        stage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
        stage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());

        VBox vBox = new VBox();

        initializeGraph(1.38 * stage.getHeight(), 0.75 * stage.getHeight());
        vBox.getChildren().add(graph);

        Scene scene = new Scene(vBox, stage.getWidth(), stage.getHeight());
        scene.getStylesheets().add("Styling/style.css");

        stage.setScene(scene);
        stage.show();
    }

    private static void initializeGraph(double width, double height) {
        NumberAxis x = new NumberAxis("X", 0, 54, 1);
        NumberAxis y = new NumberAxis("Y",0, 27, 1);

        graph = new LineChart<>(x, y);
        graph.setMinWidth(width);
        graph.setMaxWidth(width);
        graph.setPrefWidth(width);
        graph.setMinHeight(height);
        graph.setMaxHeight(height);
        graph.setPrefHeight(height);
        graph.setLegendVisible(false);
        graph.setTitle("Path");
    }
}