package ba.unsa.etf.rpr.projekat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/base.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 744, 522));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
