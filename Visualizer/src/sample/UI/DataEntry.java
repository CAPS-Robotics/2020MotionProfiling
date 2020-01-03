package sample.UI;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private static Label errorMessage;

    public static void initializeDataEntry() {
        points = new ArrayList<>();
        points.add(new DataTemplate(1));
        points.add(new DataTemplate(2));

        errorMessage = new Label();
        errorMessage.setId("error");
        errorMessage.setVisible(false);

        Button graphButton = new Button("Graph Path");
        graphButton.setOnMouseClicked(mouseEvent -> Graph.graphData(points));

        hbox = new HBox();
        hbox.setSpacing(20);
        updateUI();

        vbox = new VBox();
        vbox.setPadding(new Insets(20));
        vbox.setSpacing(20);
        vbox.getChildren().add(hbox);
        vbox.getChildren().add(errorMessage);
        vbox.getChildren().add(graphButton);
        vbox.setSpacing(10);
    }

    private static void addPoint(int pos) {
        for (int i = pos; i < points.size(); i++) {
            points.get(i).increasePos();
        }

        points.add(pos++, new DataTemplate(pos));
        updateUI();
    }

    private static void removePoint(int pos) {
        for (int i = pos; i < points.size(); i++) {
            points.get(i).decreasePos();
        }

        points.remove(pos - 1);
        updateUI();
    }

    private static void updateUI() {
        hbox.getChildren().clear();
        boolean maxReached = points.size() >= maxPoints;
        boolean minReached = points.size() <= 2;

        for (DataTemplate point: points) {
            if(maxReached) point.disableAddButton();
            else point.enableAddButton();

            if(minReached) point.disableRemoveButton();
            else point.enableRemoveButton();
            hbox.getChildren().add(point.add());
        }
    }

    public static void setErrorMessage(String message) {
        errorMessage.setText(message);
        errorMessage.setVisible(true);
    }

    public static void removeErrorMessage() {
        errorMessage.setVisible(false);
    }

    public static VBox getUIElement() { return vbox; }

    public static class DataTemplate {
        private int pos;
        private double paneWidth;

        private TextField x;
        private TextField y;
        private TextField theta;

        private Button addPoint;
        private Button removePoint;

        DataTemplate(int position) {
            pos = position;

            paneWidth = (MainUI.screenWidth - ((maxPoints + 2) * 20)) / maxPoints;

            x = new TextField();
            y = new TextField();
            theta = new TextField();
            addPoint = new Button("Add");
            removePoint = new Button("Remove");

            x.setPromptText("x");
            y.setPromptText("y");
            theta.setPromptText("angle");

            addPoint.setFocusTraversable(false);
            removePoint.setFocusTraversable(false);

            addPoint.setPrefWidth(paneWidth / 2);
            addPoint.setMinWidth(paneWidth / 2);
            addPoint.setMaxWidth(paneWidth / 2);

            removePoint.setPrefWidth(paneWidth / 2);
            removePoint.setMinWidth(paneWidth / 2);
            removePoint.setMaxWidth(paneWidth / 2);

            addPoint.setOnMouseClicked(mouseEvent -> addPoint(pos));
            removePoint.setOnMouseClicked(mouseEvent -> removePoint(pos));
        }

        public void enableAddButton() { addPoint.setDisable(false); }
        public void disableAddButton() { addPoint.setDisable(true); }
        public void enableRemoveButton() { removePoint.setDisable(false); }
        public void disableRemoveButton() { removePoint.setDisable(true); }

        public int getPos() { return pos; }
        public void increasePos() { pos++; }
        public void decreasePos() { pos--; }

        public double getX() {
            return parseDouble(x.getText());
        }
        public double getY() {
            return parseDouble(y.getText());
        }
        public double getTheta() {
            return parseDouble(theta.getText());
        }

        private double parseDouble(String s) {
            try {
                return Double.parseDouble(s);
            } catch (NumberFormatException e) {
                throw new NumberFormatException(String.valueOf(pos));
            }
        }

        public GridPane add() {
            HBox topRow = new HBox();
            topRow.getChildren().add(x);
            topRow.getChildren().add(y);
            topRow.getChildren().add(theta);

            HBox bottomRow = new HBox();
            bottomRow.getChildren().add(addPoint);
            bottomRow.getChildren().add(removePoint);

            GridPane pane = new GridPane();
            pane.add(topRow, 0, 0, 1, 1);
            pane.add(bottomRow, 0, 1, 1, 1);

            pane.setPrefWidth(paneWidth);
            pane.setMinWidth(paneWidth);
            pane.setMaxWidth(paneWidth);

            return pane;
        }
    }
}
