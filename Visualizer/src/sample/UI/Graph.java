package sample.UI;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;

public class Graph {
    private static LineChart<Number, Number> graph;

    public static void initializeGraph() {
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

        double graphHeight = 0.80 * MainUI.screenHeight;
        double graphWidth = 2 * graphHeight;

        graph = new LineChart<>(x, y);
        graph.setMinWidth(graphWidth);
        graph.setMaxWidth(graphWidth);
        graph.setPrefWidth(graphWidth);
        graph.setMinHeight(graphHeight);
        graph.setMaxHeight(graphHeight);
        graph.setPrefHeight(graphHeight);
        graph.setLegendVisible(false);
    }

    public static LineChart<Number, Number> getUIElement() { return graph; }
}
