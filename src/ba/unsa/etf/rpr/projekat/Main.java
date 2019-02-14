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
        setUserAgentStylesheet(STYLESHEET_CASPIAN);
        Locale.setDefault(new Locale("bs", "BA"));
        ResourceBundle bundle = ResourceBundle.getBundle("Trn");
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"), bundle);
        primaryStage.setTitle(bundle.getString("appName"));
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }
}


/*
1. Alati za automatsku izgradnju kode.
4. Napisati izvještaj i poslati ga.
1. Još detaljno izanalizirati program.
2. Napraviti testove.
 */
/*

dakle, pod ovim detaljno izanalizirati program podrazumjevam
nadoavati djece i odgajatelja
dodati dane u djecu i vidjeti da li se pravilno prikazuju informaicie
vidjtei kako radno vrijeme funkcionise i zezancije oko toga

isprobati jos izmjenu djece.
izmjenom imena djeteta gube se njegovi podaci sačuvani u xml.
da li ce stari xml biti izbrisan

alati za automatsku izgradnju koda uraditi ono po sablonu i doci
do toga da da se jar pokrene kako treba

dodati testove neke bezveze da sve bude pokriveno kako treba.

napraviti izvještaj da liči na nešto

i to je definitivno kraj. za ovo sve mi treba jos mozda 5 sati.

treba i nesretni class dijagram
 */

/*
VALIDACIJA JMBG NE ZABORAVI AKTIVIRATI
 */