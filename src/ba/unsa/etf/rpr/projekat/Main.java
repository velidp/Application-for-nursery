package ba.unsa.etf.rpr.projekat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
        primaryStage.setTitle("Aplikacija za vrtić");
        primaryStage.setScene(new Scene(root, 744, 522));
        primaryStage.show();
    }

    public static void main(String[] args) { launch(args); }
}
/*
problemi do sad:
isti direktor za više ustanova.
validacija
povezanost ustanove sa svim ostalim
brisanje i roditelja prilikom brisanja djeteta.

 */