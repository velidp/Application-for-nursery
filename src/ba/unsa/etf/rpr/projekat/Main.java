package ba.unsa.etf.rpr.projekat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
        KindergartenDAO base = new KindergartenDAO();

        Child child1 = (Child) base.getAllChildren().get(0);
        Child child2 = (Child) base.getAllChildren().get(1);
        Child child3 = (Child) base.getAllChildren().get(2);

        //1.
        ArrayList<Day> days1 = child1.desrialize();

        Day posljednjiDan1 = days1.get(days1.size()-1);

        days1 = new ArrayList<Day>();

        Day dayA = new Day();
        dayA.setApsent(true);
        dayA.setArrivalTime(LocalDateTime.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth(), 10, 30, 0));
        dayA.setDepartureTime(LocalDateTime.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth(), 19, 0, 0));
        dayA.setDate(LocalDate.now().minusDays(1));
        ArrayList komentari = new ArrayList<String>();
        komentari.add("komentar1");
        komentari.add("komentar2");
        dayA.setComments(komentari);

        Day dayB = new Day();
        dayB.setApsent(true);
        dayB.setArrivalTime(LocalDateTime.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth(), 10, 0, 0));
        dayB.setDepartureTime(LocalDateTime.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth(), 18, 0, 0));
        dayB.setDate(LocalDate.now().minusDays(2));
        ArrayList komentari1 = new ArrayList<String>();
        komentari1.add("neki komentar");
        komentari1.add("melo lp");
        dayB.setComments(komentari1);


        days1.add(dayB);
        days1.add(dayA);
        days1.add(posljednjiDan1);

        child1.serialize(days1);



        launch(args);
    }
}

/*
VALIDACIJA JMBG NE ZABORAVI AKTIVIRATI
 */
/*
u biti imam jos uradite sljedece:

8. izmjena djeteta
4. testovi
5. maven
6. class dijagram
7. izvjštaj
 */

