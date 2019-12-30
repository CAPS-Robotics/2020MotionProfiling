package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import static sample.HomeUI.setUI;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        HomeUI.setUI(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
