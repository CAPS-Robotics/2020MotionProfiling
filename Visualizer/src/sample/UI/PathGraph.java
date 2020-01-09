package sample.UI;

import MotionProfiling.Spline;
import MotionProfiling.VelocityProfile;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;

import java.util.ArrayList;

public class PathGraph {
    private static LineChart<Number, Number> graph;

    private static HBox hbox;

    private static Label pathDistance;
    private static Label pathTime;

    public static void initializeGraph() {
        double xLength = 52.4375 / 1674 * 1826;
        double yHeight = 26.9375 / 861 * 958;

        NumberAxis x = new NumberAxis("", 0, xLength, 1);
        double xHeight = 20;
        x.setPrefHeight(xHeight);
        x.setMinHeight(xHeight);
        x.setMaxHeight(xHeight);

        NumberAxis y = new NumberAxis("",0, yHeight, 1);
        double yWidth = 40;
        y.setPrefWidth(yWidth);
        y.setMinWidth(yWidth);
        y.setMaxWidth(yWidth);

        double graphHeight = 0.80 * MainUI.screenHeight;
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

        pathDistance = new Label("Path Distance: ");
        pathTime = new Label("Path Time: ");

        VBox vbox = new VBox();
        vbox.getChildren().add(pathDistance);
        vbox.getChildren().add(pathTime);
        vbox.setSpacing(20);

        hbox = new HBox();
        hbox.getChildren().add(graph);
        hbox.getChildren().add(vbox);
        hbox.setSpacing((MainUI.screenWidth - graphWidth) / 2 - 50);
    }

    public static void graphData(ArrayList<DataEntry.DataTemplate> points) {
        ArrayList<Spline> path = new ArrayList<>();
        boolean badData = false;

        for (int i = 0; i < points.size() - 1; i++) {
            try {
                DataEntry.DataTemplate p0 = points.get(i);
                double x0 = p0.getX();
                double y0 = p0.getY();
                double theta0 = p0.getTheta();
                if(isDataOutOfRange(x0, y0, theta0)) throw new Exception(String.valueOf(p0.getPos()));

                DataEntry.DataTemplate p1 = points.get(i + 1);
                double x1 = p1.getX();
                double y1 = p1.getY();
                double theta1 = p1.getTheta();
                if(isDataOutOfRange(x1, y1, theta1)) throw new Exception(String.valueOf(p1.getPos()));

                path.add(new Spline(x0, y0, x1, y1, 90-theta0, 90-theta1));
            } catch (NumberFormatException e) {
                DataEntry.setErrorMessage("Invalid data at point " + e.getMessage());
                badData = true;
            } catch (Exception e) {
                DataEntry.setErrorMessage("Data out of range at point " + e.getMessage());
                badData = true;
            }
        }

        if(!badData) {
            VelocityProfile.setPath(path);
            VelocityProfile.calculateDistance();
            VelocityProfile.calculateVelocities();

            pathDistance.setText(String.format("Path Distance: %.3f %n", VelocityProfile.getPathDistance()));
            pathTime.setText(String.format("Path Time: %.3f %n", VelocityProfile.getPathTime()));
            DataEntry.removeErrorMessage();
            graph.getData().clear();

            XYChart.Series<Number, Number> series;
            for (Spline spline : path) {
                series = new XYChart.Series<>();
                for (double t = 0; t <= 1; t += 0.001) {
                    series.getData().add(new XYChart.Data<>(spline.getX(t), spline.getY(t)));
                }
                graph.getData().add(series);
            }
        }
    }

    private static boolean isDataOutOfRange(double x, double y, double theta) {
        return x < 0 || x > 55 || y < 0 || y > 28.5 || theta < -180 || theta > 180;
    }

    public static HBox getUIElement() { return hbox; }
}
