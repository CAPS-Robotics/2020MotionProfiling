package sample;

import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class HomeUI {
    static LineChart<Number, Number> graph;

    public static void setUI(Stage stage) {
        stage.setTitle("Pathfinding Visualizer");

        stage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
        stage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());

        VBox vBox = new VBox();

        setGraph(1.5 * stage.getHeight(), 0.75 * stage.getHeight());
        vBox.getChildren().add(graph);

        stage.setScene(new Scene(vBox, stage.getWidth(), stage.getHeight()));
        stage.show();
    }

    private static void setGraph(double width, double height) {
        NumberAxis x = new NumberAxis(0, 54, 1);
        x.setLabel("X");
        NumberAxis y = new NumberAxis(0, 27, 1);
        y.setLabel("Y");

        graph = new LineChart<>(x, y);

        graph.setMinWidth(width);
        graph.setMaxWidth(width);
        graph.setMinHeight(height);
        graph.setMaxHeight(height);

        graph.setTitle("Path");
    }
}
