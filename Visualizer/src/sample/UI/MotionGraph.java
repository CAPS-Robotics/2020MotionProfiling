package sample.UI;

import MotionProfiling.Spline;
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
        double graphHeight = (MainUI.screenHeight - 60) / 2;

        NumberAxis robotX = new NumberAxis();
        robotX.setLabel("Time (s)");
        NumberAxis robotY = new NumberAxis();
        robotY.setLabel("Velocity (ft/s)");

        robotVelocityGraph = new LineChart(robotX, robotY);
        robotVelocityGraph.setTitle("Robot Velocities");
        robotVelocityGraph.setPrefHeight(graphHeight);
        robotVelocityGraph.setMinHeight(graphHeight);
        robotVelocityGraph.setMaxHeight(graphHeight);
        robotVelocityGraph.setCreateSymbols(false);
        robotVelocityGraph.setLegendVisible(false);
        robotVelocityGraph.setAxisSortingPolicy(LineChart.SortingPolicy.NONE);

        NumberAxis motorX = new NumberAxis();
        motorX.setLabel("Time (s)");
        NumberAxis motorY = new NumberAxis();
        motorY.setLabel("Velocity (ft/s)");

        motorVelocityGraph = new LineChart(motorX, motorY);
        motorVelocityGraph.setTitle("Motor Velocities");
        motorVelocityGraph.setPrefHeight(graphHeight);
        motorVelocityGraph.setMinHeight(graphHeight);
        motorVelocityGraph.setMaxHeight(graphHeight);
        motorVelocityGraph.setCreateSymbols(false);
        motorVelocityGraph.setLegendVisible(true);
        motorVelocityGraph.setAxisSortingPolicy(LineChart.SortingPolicy.NONE);

        graphData();
    }

    public static void graphData() {
        ArrayList<Double> times = VelocityProfile.getTimes();
        ArrayList<Double> leftVelocities = VelocityProfile.getLeftVelocities();
        ArrayList<Double> rightVelocities = VelocityProfile.getRightVelocities();
        ArrayList<Double> velocities = VelocityProfile.getVelocities();

        XYChart.Series robotData = new XYChart.Series();
        XYChart.Series testSeries = new XYChart.Series();
        XYChart.Series leftData =  new XYChart.Series();
        XYChart.Series rightData = new XYChart.Series();

        leftData.setName("Left Motor");
        rightData.setName("Right Motor");

        for(int i = 0; i < times.size(); i++) {
            double time = times.get(i);
            //robotData.getData().add(new XYChart.Data<Number, Number>(time, velocities.get(i)));
        }

        double dydx = 0;
        /*for(Spline spline : VelocityProfile.getPath()) {
            for(int t = 1; t < times.size(); t++) {
                robotData.getData().add(new XYChart.Data<Number, Number>(times.get(t), t));
                Spline spline1 = new Spline(times.get(t - 1), t - 1, times.get(t), t, dydx, 1 / (times.get(t) - times.get(t - 1)));
                dydx = spline1.getdydx(0);
                testSeries.getData().add(new XYChart.Data<Number, Number>(spline1.getX(1 / times.size()), t));
            }
        }*/

        for(double time = 0; time < VelocityProfile.getPathTime(); time += 0.005) {
            VelocityProfile.calculateCurrentVelocities(time);
            leftData.getData().add(new XYChart.Data<Number, Number>(time, VelocityProfile.getCurrentLeftVelocity()));
            rightData.getData().add(new XYChart.Data<Number, Number>(time, VelocityProfile.getCurrentRightVelocity()));
        }

        robotVelocityGraph.getData().clear();
        robotVelocityGraph.getData().add(robotData);
        robotVelocityGraph.getData().add(testSeries);

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
