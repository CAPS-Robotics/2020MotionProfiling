package sample;

import javafx.geometry.Insets;
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
        vBox.setPadding(new Insets(10, 0, 0, 0));

        double graphHeight = 0.60 * stage.getHeight();
        double graphWidth = 2 * graphHeight;

        vBox.getChildren().add(initializeGraph(graphWidth, graphHeight));

        Scene scene = new Scene(vBox, stage.getWidth(), stage.getHeight());
        scene.getStylesheets().add("Styling/style.css");

        stage.setScene(scene);
        stage.show();
    }

    private static LineChart<Number, Number> initializeGraph(double width, double height) {
        NumberAxis x = new NumberAxis("", 0, 54, 1);
        double xHeight = 20;
        x.setPrefHeight(xHeight);
        x.setMinHeight(xHeight);
        x.setMaxHeight(xHeight);

        NumberAxis y = new NumberAxis("",0, 27, 1);
        double yWidth = 40;
        y.setPrefWidth(yWidth);
        y.setMinWidth(yWidth);
        y.setMaxWidth(yWidth);

        graph = new LineChart<>(x, y);
        graph.setMinWidth(width);
        graph.setMaxWidth(width);
        graph.setPrefWidth(width);
        graph.setMinHeight(height);
        graph.setMaxHeight(height);
        graph.setPrefHeight(height);
        graph.setLegendVisible(false);

        return graph;
    }
}