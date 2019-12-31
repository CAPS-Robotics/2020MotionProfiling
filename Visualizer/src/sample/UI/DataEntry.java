package sample.UI;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class DataEntry {
    private static final int maxPoints = 10;
    private static ArrayList<DataTemplate> points;
    private static VBox vbox;
    private static HBox hbox;

    public static void initializeDataEntry() {
        points = new ArrayList<>();
        points.add(new DataTemplate(1));
        points.add(new DataTemplate(2));

        hbox = new HBox();
        hbox.setSpacing(20);
        hbox.getChildren().add(points.get(0).add());
        hbox.getChildren().add(points.get(1).add());

        Button graphButton = new Button("Graph Path");
        graphButton.setFocusTraversable(false);

        vbox = new VBox();
        vbox.setPadding(new Insets(20));
        vbox.setSpacing(20);
        vbox.getChildren().add(hbox);
        vbox.getChildren().add(graphButton);
    }

    private static void addPoint(int pos) {
        for (int i = pos; i < points.size(); i++) {
            points.get(i).increasePos();
        }

        points.add(pos++, new DataTemplate(pos));

        updateUI();
    }

    private static void updateUI() {
        hbox.getChildren().clear();
        boolean maxReached = points.size() >= maxPoints;

        for (DataTemplate point: points) {
            if(maxReached) point.disableButton();
            hbox.getChildren().add(point.add());
        }
    }

    public static VBox getUIElement() { return vbox; }

    private static class DataTemplate {
        private int pos;
        private double paneWidth;

        private TextField x;
        private TextField y;
        private TextField theta;
        private Button addPoint;

        DataTemplate(int position) {
            pos = position;

            paneWidth = (MainUI.screenWidth - ((maxPoints + 2) * 20)) / maxPoints;

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

            addPoint.setOnMouseClicked(mouseEvent -> addPoint(pos));
        }

        public void disableButton() { addPoint.setDisable(true);}

        public void increasePos() { pos++; }

        public GridPane add() {
            GridPane pane = new GridPane();
            pane.add(x, 0, 0, 1, 1);
            pane.add(y, 1, 0, 1, 1);
            pane.add(theta, 0, 1, 1, 1);
            pane.add(addPoint, 1, 1, 1, 1);

            pane.setPrefWidth(paneWidth);
            pane.setMinWidth(paneWidth);
            pane.setMaxWidth(paneWidth);

            return pane;
        }
    }
}
