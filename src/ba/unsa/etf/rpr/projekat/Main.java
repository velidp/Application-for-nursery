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
testiranje
main i printreport se ne mogu testirati

ostaje pitanje kako testirati kontrolere i DAO...

dakle ideja je pomocu robota testirati cijeli graficki interfejs ovo treba fino osmisliti...
i na kraju vidjet sta nije pokriveno testovima i pokusat i to testirati...

 */
/*
u biti plan je sljedeci:
1. zavrsiti testove
2. napravit class dijagram
3. meaven
4. napisati izvještaj
5. preostalo vrijeme uloziti na popravljanje gresaka i uljepsavanje
 */

