package sample.UI;

import MotionProfiling.VelocityProfile;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;

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

        ArrayList<Double> times = VelocityProfile.getTimes();
        ArrayList<Double> velocities = VelocityProfile.getVelocities();

        for(int i = 0; i < times.size(); i++) {
            series.getData().add(new XYChart.Data<Number, Number>(times.get(i), velocities.get(i)));
        }

        graph.getData().clear();
        graph.getData().add(series);
    }

    public static LineChart getGraph() { return graph; }
}
