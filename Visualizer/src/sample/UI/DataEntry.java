package sample.UI;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class DataEntry {
    private int nPoints = 2;
    static ArrayList<DataTemplate> points;

    public static HBox setupDataEntry() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(20));
        hbox.setSpacing(20);

        points = new ArrayList<>();
        points.add(new DataTemplate(1));
        points.add(new DataTemplate(2));

        hbox.getChildren().add(points.get(0).add());
        hbox.getChildren().add(points.get(1).add());
        return hbox;
    }

    private static class DataTemplate extends Node {
        int pointNumber;

        TextField x;
        TextField y;
        TextField theta;
        Button addPoint;

        DataTemplate(int pointNumber) {
            this.pointNumber = pointNumber;

            x = new TextField();
            y = new TextField();
            theta = new TextField();
            addPoint = new Button("Add Point");

            x.setPromptText("x");
            y.setPromptText("y");
            theta.setPromptText("angle");

            x.setFocusTraversable(false);
            y.setFocusTraversable(false);
            theta.setFocusTraversable(false);
            addPoint.setFocusTraversable(false);
        }

        public GridPane add() {
            GridPane pane = new GridPane();
            pane.add(x, 0, 0, 1, 1);
            pane.add(y, 1, 0, 1, 1);
            pane.add(theta, 0, 1, 1, 1);
            pane.add(addPoint, 1, 1, 1, 1);
            return pane;
        }
    }
}
