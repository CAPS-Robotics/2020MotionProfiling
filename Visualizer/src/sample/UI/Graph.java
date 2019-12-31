package sample.UI;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;

public class Graph {
    private static LineChart<Number, Number> graph;

    public static void initializeGraph(double width, double height) {
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
    }

    public static LineChart<Number, Number> getUIElement() { return graph; }
}
