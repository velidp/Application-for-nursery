package ba.unsa.etf.rpr.projekat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
        primaryStage.getIcons().add(new Image("img/child_1191970.png"));
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

/*
VALIDACIJA JMBG NE ZABORAVI AKTIVIRATI
 */
/*
u biti imam jos uradite sljedece:

4. testovi
5. maven


6. class dijagram
7. izvjštaj
 */
/*
4. testovi - testirat sto se testirat moze
5. maeven po sablonu

6 i 7 class dijagram i izvještaj napravit napisat.
 */
/*
testiranje
main i printreport se ne mogu testirati

ostaje pitanje kako testirati kontrolere i DAO...
 */

