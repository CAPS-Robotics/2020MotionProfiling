package sample.UI;

import MotionProfiling.VelocityProfile;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;

import javax.sound.sampled.Line;
import java.nio.channels.NotYetBoundException;
import java.util.ArrayList;

public class MotionGraph {
    private static LineChart robotVelocityGraph;
    private static LineChart motorVelocityGraph;

    public static void initializeGraph() {
        NumberAxis robotX = new NumberAxis();
        robotX.setLabel("Time (s)");
        NumberAxis robotY = new NumberAxis();
        robotY.setLabel("Velocity (ft/s)");

        robotVelocityGraph = new LineChart(robotX, robotY);
        motorVelocityGraph.setTitle("Robot Velocities");
        robotVelocityGraph.setCreateSymbols(false);
        robotVelocityGraph.setLegendVisible(false);
        robotVelocityGraph.setAxisSortingPolicy(LineChart.SortingPolicy.NONE);

        NumberAxis motorX = new NumberAxis();
        motorX.setLabel("Time (s)");
        NumberAxis motorY = new NumberAxis();
        motorY.setLabel("Velocity (ft/s)");

        motorVelocityGraph = new LineChart(motorX, motorY);
        motorVelocityGraph.setTitle("Motor Velocities");
        motorVelocityGraph.setCreateSymbols(false);
        robotVelocityGraph.setLegendVisible(true);
        robotVelocityGraph.setAxisSortingPolicy(LineChart.SortingPolicy.NONE);

        graphData();
    }

    public static void graphData() {
        ArrayList<Double> times = VelocityProfile.getTimes();
        ArrayList<Double> leftVelocities = VelocityProfile.getLeftVelocities();
        ArrayList<Double> rightVelocities = VelocityProfile.getRightVelocities();
        ArrayList<Double> velocities = VelocityProfile.getVelocities();

        XYChart.Series robotData = new XYChart.Series();
        XYChart.Series leftData =  new XYChart.Series();
        XYChart.Series rightData = new XYChart.Series();

        for(int i = 0; i < times.size(); i++) {
            double time = times.get(i);
            robotData.getData().add(new XYChart.Data<Number, Number>(time, velocities.get(i)));
            leftData.getData().add(new XYChart.Data<Number, Number>(time, leftVelocities.get(i)));
            rightData.getData().add(new XYChart.Data<Number, Number>(time, rightVelocities.get(i)));
        }

        robotVelocityGraph.getData().clear();
        robotVelocityGraph.getData().add(robotData);

        motorVelocityGraph.getData().clear();
        motorVelocityGraph.getData().add(leftData);
        motorVelocityGraph.getData().add(rightData);
    }

    public static VBox getUIElement() {
        VBox vbox = new VBox();
        vbox.getChildren().add(robotVelocityGraph);
        vbox.getChildren().add(motorVelocityGraph);
        return vbox;
    }
}
