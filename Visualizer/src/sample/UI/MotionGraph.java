package sample.UI;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class MotionGraph {
    private static LineChart graph;

    public static void initializeGraph() {
        NumberAxis x = new NumberAxis();
        x.setLabel("Time (s)");
        NumberAxis y = new NumberAxis();
        y.setLabel("Velocity (ft/s)");

        graph = new LineChart(x, y);
        graph.setCreateSymbols(false);
        graph.setLegendVisible(false);
        graph.setAxisSortingPolicy(LineChart.SortingPolicy.NONE);

        graphData();
    }

    public static void graphData() {
        XYChart.Series series = new XYChart.Series();

        series.getData().add(new XYChart.Data<Number, Number>(2, 4));
        series.getData().add(new XYChart.Data<Number, Number>(4, 8));
        series.getData().add(new XYChart.Data<Number, Number>(9, 18));

        graph.getData().add(series);
    }

    public static LineChart getGraph() { return graph; }
}
