package sample.UI;

import MotionProfiling.Point;
import MotionProfiling.Spline;
import MotionProfiling.VelocityProfile;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class PathGraph {
    private static LineChart<Number, Number> graph;

    private static HBox hbox;

    private static Label pathDistance;
    private static Label pathTime;

    public static void initializeGraph() {
        double xLength = 26.9375 / 861 * 958;
        double yHeight = 52.4375 / 1674 * 1826;

        NumberAxis x = new NumberAxis("", -xLength / 2, xLength / 2, 1);
        double xHeight = 40;
        x.setPrefHeight(xHeight);
        x.setMinHeight(xHeight);
        x.setMaxHeight(xHeight);

        NumberAxis y = new NumberAxis("",0, yHeight, 1);
        double yWidth = xLength / yHeight * xHeight;
        y.setPrefWidth(yWidth);
        y.setMinWidth(yWidth);
        y.setMaxWidth(yWidth);

        double graphHeight = 0.90 * MainUI.screenHeight;
        double graphWidth = xLength / yHeight * graphHeight;

        graph = new LineChart<>(x, y);
        graph.setMinWidth(graphWidth);
        graph.setMaxWidth(graphWidth);
        graph.setPrefWidth(graphWidth);
        graph.setMinHeight(graphHeight);
        graph.setMaxHeight(graphHeight);
        graph.setPrefHeight(graphHeight);
        graph.setCreateSymbols(false);
        graph.setLegendVisible(false);
        graph.setAxisSortingPolicy(LineChart.SortingPolicy.NONE);
    }

    public static void graphData(ArrayList<DataEntry.DataTemplate> points) {
        boolean badData = false;
        VelocityProfile.reset();

        for (int i = 0; i < points.size(); i++) {
            try {
                DataEntry.DataTemplate p = points.get(i);
                VelocityProfile.addWaypoint(new Point(p.getX(), p.getY(), p.getTheta()));
                if(isDataOutOfRange(p.getX(), p.getY(), p.getTheta())) throw new Exception(String.valueOf(p.getPos()));
            } catch (NumberFormatException e) {
                DataEntry.setErrorMessage("Invalid data at point " + e.getMessage());
                badData = true;
            } catch (Exception e) {
                DataEntry.setErrorMessage("Data out of range at point " + e.getMessage());
                badData = true;
            }
        }

        if(!badData) {
            DataEntry.enableProfiling();

            VelocityProfile.generatePath();

            //pathDistance.setText(String.format("Path Distance: %.3f %n", VelocityProfile.getPathDistance()));
            //pathTime.setText(String.format("Path Time: %.3f %n", VelocityProfile.getPathTime()));
            DataEntry.removeErrorMessage();
            graph.getData().clear();

            XYChart.Series<Number, Number> series;
            XYChart.Series<Number, Number> leftSeries;
            XYChart.Series<Number, Number> rightSeries;

            for (Spline spline : VelocityProfile.getPath()) {
                series = new XYChart.Series<>();
                leftSeries = new XYChart.Series<>();
                leftSeries.setName("left");
                rightSeries = new XYChart.Series<>();
                rightSeries.setName("right");
                for (double t = 0; t <= 1; t += 0.001) {
                    series.getData().add(new XYChart.Data<>(spline.getX(t), spline.getY(t)));
                    leftSeries.getData().add(new XYChart.Data<>(spline.getLeftPosX(t), spline.getLeftPosY(t)));
                    rightSeries.getData().add(new XYChart.Data<>(spline.getRightPosX(t), spline.getRightPosY(t)));
                }

                graph.getData().add(series);
                graph.getData().add(leftSeries);
                graph.getData().add(rightSeries);
            }
        }
    }

    private static boolean isDataOutOfRange(double x, double y, double theta) {
        return x < -15 || x > 15 || y < 0 || y > 55 || theta < -180 || theta > 180;
    }

    public static LineChart getUIElement() { return graph; }
}
