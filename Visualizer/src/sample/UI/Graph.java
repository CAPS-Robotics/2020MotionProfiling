package sample.UI;

import MotionProfiling.Spline;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;

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

    public static void graphData(ArrayList<DataEntry.DataTemplate> points) {
        ArrayList<Spline> path = new ArrayList<>();

        for (int i = 0; i < points.size() - 1; i++) {
            DataEntry.DataTemplate p0 = points.get(i);
            DataEntry.DataTemplate p1 = points.get(i+1);

            path.add(new Spline(p0.getX(), p0.getY(), p1.getX(), p1.getY(), p0.getTheta(), p1.getTheta()));
        }

        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        for(Spline spline : path) {
            for(double t = 0; t <= 1; t += 0.01) {
                series.getData().add(new XYChart.Data<>(spline.getX(t), spline.getY(t)));
            }
        }

        graph.getData().clear();
        graph.getData().add(series);
    }

    public static LineChart<Number, Number> getUIElement() { return graph; }
}
