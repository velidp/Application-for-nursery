package ba.unsa.etf.rpr.projekat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Locale.setDefault(new Locale("bs", "BA"));
        ResourceBundle bundle = ResourceBundle.getBundle("Trn");
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"), bundle);
        primaryStage.setTitle("Aplikacija za vrtić");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }
}
/*
Agenda:
->internacionalizacija
->testovi
->alati za automatsku izgradnju koda

Dakle, imam još uraditi sljedeće.
1. U glavnom prozoru dadati odabir jezika tj. bosanski ili engleski.
I prilagodit da se koristi jezik koji je odabran. Vidjeti još one prevode.

2. Vidjeti alate za automatsku izgradnju koda tj. posljednje što treba uraditi u nekom projektu.

3. Ručno istestirati sve. Koristit aplikaciju. Popraviti greške.

4. Dodati testovo.

5. Napisati izvještaj i poslati ga.

 */