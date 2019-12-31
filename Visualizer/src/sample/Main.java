package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import sample.UI.MainUI;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        MainUI.setUI(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
