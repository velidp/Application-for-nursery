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

    public static void main(String[] args) {
        launch(args);

    }
}
/*
Agenda:
->validacija
->izvještavanje
->šminkanje (pod ovim podrazumjevam naći način da upotrebim sve ostale stvari)


dakle prvo cu napraviti scenario kao da je radna otvorena prvi put u toku radnog vremena
zatim cu napraviti scenario u kojem se provjerava da li je zaista radna otvorena prvi put u toku radnog vremena
mislim da ce ovo drugo biti strasno komplikovano...
 */