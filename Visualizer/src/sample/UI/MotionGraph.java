package sample.UI;

import MotionProfiling.LinearApproximation;
import MotionProfiling.VelocityProfile;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;

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
        robotVelocityGraph.setLegendVisible(true);
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
        XYChart.Series robotData = new XYChart.Series();
        XYChart.Series testData = new XYChart.Series();
        XYChart.Series leftData =  new XYChart.Series();
        XYChart.Series rightData = new XYChart.Series();

        leftData.setName("Left Motor");
        rightData.setName("Right Motor");
        robotData.setName("Actual");

        for(double distance = 0.1; distance < VelocityProfile.getPathDistance(); distance += 0.1) {
            VelocityProfile.calculateVelocities(distance);
            if(!Double.isNaN(VelocityProfile.getCurrentLeftVelocity()) && !Double.isNaN(VelocityProfile.getCurrentRightVelocity())) {
                leftData.getData().add(new XYChart.Data<Number, Number>(distance, VelocityProfile.getCurrentLeftVelocity()));
                rightData.getData().add(new XYChart.Data<Number, Number>(distance, VelocityProfile.getCurrentRightVelocity()));
            } else {
            }
        }

        robotVelocityGraph.getData().clear();
        robotVelocityGraph.getData().add(robotData);
        robotVelocityGraph.getData().add(testData);

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
