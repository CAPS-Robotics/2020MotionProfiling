package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        setUI(primaryStage);
    }

    private void setUI(Stage stage) {
        stage.setTitle("Pathfinding Visualizer");

        // Make the stage take up the entire screen
        stage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
        stage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());

        VBox vBox = new VBox();

        stage.setScene(new Scene(vBox, stage.getWidth(), stage.getHeight()));
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
