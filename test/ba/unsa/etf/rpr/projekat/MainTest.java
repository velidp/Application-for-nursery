package ba.unsa.etf.rpr.projekat;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

import static javafx.application.Application.STYLESHEET_CASPIAN;
import static javafx.application.Application.setUserAgentStylesheet;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class MainTest {
    KindergartenDAO base = new KindergartenDAO();


    @Start
    public void start (Stage stage) throws Exception {
        setUserAgentStylesheet(STYLESHEET_CASPIAN);
        Locale.setDefault(new Locale("bs", "BA"));
        ResourceBundle bundle = ResourceBundle.getBundle("Trn");
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"), bundle);
        Stage primaryStage = new Stage();
        primaryStage.setTitle(bundle.getString("appName"));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("img/child_1191970.png"));
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    @Test
    public void test (FxRobot robot) {

        robot.clickOn("#institutionCombo");
        robot.clickOn("#addInstitutionButton");
        robot.clickOn("#nameOfInstitution");

        robot.clickOn("#okButton2");

        TextField ime = robot.lookup("#nameOfInstitution").queryAs(TextField.class);
        Background bg = ime.getBackground();
        boolean colorFound = false;
        for (BackgroundFill bf : bg.getFills()) {
            if (bf.getFill().toString().contains(""))
                colorFound = true;
        }
        assertTrue(colorFound);
        robot.clickOn("#nameOfInstitution");
        robot.write("First Institution");
        robot.clickOn("#addPlaceOfInstitutionButton");

        /*dodajemo mjesto*/

        /*unosimo nevalidne podatke tj. ostavljamo prazno*/
        robot.clickOn("#nameOfPlaceField");
        robot.clickOn("#okButtonPlace");

        // Forma nije validna i nece se zatvoriti!
        ime = robot.lookup("#nameOfPlaceField").queryAs(TextField.class);
        bg = ime.getBackground();
        colorFound = false;
        for (BackgroundFill bf : bg.getFills()) {
            if (bf.getFill().toString().contains(""))
                colorFound = true;
        }
        assertTrue(colorFound);

        //sada unosimo ispravne podatke
        robot.clickOn("#nameOfPlaceField");
        robot.write("Sarajevo");
        robot.clickOn("#adressOfPlaceField");
        robot.write("Sarajevo b.b.");
        robot.clickOn("#zipCodeField");
        robot.write("71000");
        robot.clickOn("#okButtonPlace");

        assertEquals(base.getPlaces().size(), base.getMaxIdFromPlaces()-1);
        //mjesto je sada dodano


        robot.clickOn("#phoneNumberField");


        robot.clickOn("#okButton2");

        ime = robot.lookup("#phoneNumberField").queryAs(TextField.class);
        bg = ime.getBackground();
        colorFound = false;
        for (BackgroundFill bf : bg.getFills()) {
            if (bf.getFill().toString().contains(""))
                colorFound = true;
        }
        assertTrue(colorFound);

        robot.clickOn("#phoneNumberField");
        robot.write("060111222");



        robot.clickOn("#emailField");
        robot.clickOn("#okButton2");

        ime = robot.lookup("#emailField").queryAs(TextField.class);
        bg = ime.getBackground();
        colorFound = false;
        for (BackgroundFill bf : bg.getFills()) {
            if (bf.getFill().toString().contains(""))
                colorFound = true;
        }
        assertTrue(colorFound);
        robot.clickOn("#emailField");
        robot.write("firstinstitution@gmail.com");

//////////////////////////
        robot.clickOn("#nameOfDirectorField");

        robot.clickOn("#okButton2");

        ime = robot.lookup("#nameOfDirectorField").queryAs(TextField.class);
        bg = ime.getBackground();
        colorFound = false;
        for (BackgroundFill bf : bg.getFills()) {
            if (bf.getFill().toString().contains(""))
                colorFound = true;
        }
        assertTrue(colorFound);
        robot.clickOn("#nameOfDirectorField");
        robot.write("Direktor");

//////////////////////////
        robot.clickOn("#surenameOfDirectorField");
        robot.clickOn("#okButton2");

        ime = robot.lookup("#surenameOfDirectorField").queryAs(TextField.class);
        bg = ime.getBackground();
        colorFound = false;
        for (BackgroundFill bf : bg.getFills()) {
            if (bf.getFill().toString().contains(""))
                colorFound = true;
        }
        assertTrue(colorFound);
        robot.clickOn("#surenameOfDirectorField");
        robot.write("Direktorovic");

///////////////////////////////////////////////
        robot.clickOn("#jmbgOfDirectorField");
        robot.write("0102987143");
        robot.clickOn("#okButton2");

        ime = robot.lookup("#jmbgOfDirectorField").queryAs(TextField.class);
        bg = ime.getBackground();
        colorFound = false;
        for (BackgroundFill bf : bg.getFills()) {
            if (bf.getFill().toString().contains(""))
                colorFound = true;
        }
        assertTrue(colorFound);
        robot.clickOn("#jmbgOfDirectorField");
        robot.write("352");


        /////////////////////////////////////

        DatePicker date = robot.lookup("#dateOfBirthOfDirectorField").queryAs(DatePicker.class);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        date.setValue(LocalDate.parse("01.02.1987", formatter));
//////////////////////////////////////////////////

        robot.clickOn("#phoneNumberOfDirectorField");
        robot.clickOn("#okButton2");

        ime = robot.lookup("#phoneNumberOfDirectorField").queryAs(TextField.class);
        bg = ime.getBackground();
        colorFound = false;
        for (BackgroundFill bf : bg.getFills()) {
            if (bf.getFill().toString().contains(""))
                colorFound = true;
        }
        assertTrue(colorFound);
        robot.clickOn("#phoneNumberOfDirectorField");
        robot.write("061000222");


        robot.clickOn("#emailOfDirectorField");
        robot.clickOn("#okButton2");

        ime = robot.lookup("#emailOfDirectorField").queryAs(TextField.class);
        bg = ime.getBackground();
        colorFound = false;
        for (BackgroundFill bf : bg.getFills()) {
            if (bf.getFill().toString().contains(""))
                colorFound = true;
        }
        assertTrue(colorFound);
        robot.clickOn("#emailOfDirectorField");
        robot.write("direktor@gmail.com");

        robot.clickOn("#okButton2");

        assertEquals(base.getInstitutions().size(), base.getMaxIdFromInstitutions()-1);

        //doana je ustanova i mjesto
        //sada treba dodati djete

        robot.clickOn("#addPerson");


        ///////////////////////////////
        /////////////////////////////
        robot.clickOn("#nameField");
        robot.clickOn("#okButton3");

        ime = robot.lookup("#nameField").queryAs(TextField.class);
        bg = ime.getBackground();
        colorFound = false;
        for (BackgroundFill bf : bg.getFills()) {
            if (bf.getFill().toString().contains(""))
                colorFound = true;
        }
        assertTrue(colorFound);
        robot.clickOn("#nameField");
        robot.write("Djete");
        ////////////////////////
        ////////////////////////
        ///////////////////////////////
        /////////////////////////////
        robot.clickOn("#surenameField");
        robot.clickOn("#okButton3");

        ime = robot.lookup("#surenameField").queryAs(TextField.class);
        bg = ime.getBackground();
        colorFound = false;
        for (BackgroundFill bf : bg.getFills()) {
            if (bf.getFill().toString().contains(""))
                colorFound = true;
        }
        assertTrue(colorFound);
        robot.clickOn("#surenameField");
        robot.write("Djetovic");
        ///////////////////////////////
        /////////////////////////////
        robot.clickOn("#nameParentField");
        robot.clickOn("#okButton3");

        ime = robot.lookup("#nameParentField").queryAs(TextField.class);
        bg = ime.getBackground();
        colorFound = false;
        for (BackgroundFill bf : bg.getFills()) {
            if (bf.getFill().toString().contains(""))
                colorFound = true;
        }
        assertTrue(colorFound);
        robot.clickOn("#nameParentField");
        robot.write("Roditelj");
        /////////////////////////////
        /////////////////////////////
        //////////////////////////////
        robot.clickOn("#surenameParrentField");
        robot.clickOn("#okButton3");

        ime = robot.lookup("#surenameParrentField").queryAs(TextField.class);
        bg = ime.getBackground();
        colorFound = false;
        for (BackgroundFill bf : bg.getFills()) {
            if (bf.getFill().toString().contains(""))
                colorFound = true;
        }
        assertTrue(colorFound);
        robot.clickOn("#surenameParrentField");
        robot.write("Roditeljovic");
        /////////////////////////
        ///////////////////////////
        robot.clickOn("#phoneNumberField");
        robot.clickOn("#okButton3");

        ime = robot.lookup("#phoneNumberField").queryAs(TextField.class);
        bg = ime.getBackground();
        colorFound = false;
        for (BackgroundFill bf : bg.getFills()) {
            if (bf.getFill().toString().contains(""))
                colorFound = true;
        }
        assertTrue(colorFound);
        robot.clickOn("#phoneNumberField");
        robot.write("063999888");
        ///////////////


        robot.clickOn("#specialNeedsNo");
        robot.clickOn("#specialNeedsYes");
        robot.clickOn("#specialNeedsNo");


        robot.clickOn("#jmbgField");
        robot.write("0210017153301");

        date = robot.lookup("#dateField").queryAs(DatePicker.class);

        date.setValue(LocalDate.parse("02.10.2017", formatter));

        robot.clickOn("#jmbgParentField");
        robot.write("0101990153338");

        date = robot.lookup("#dateParrentField").queryAs(DatePicker.class);

        date.setValue(LocalDate.parse("01.01.1990", formatter));






        robot.clickOn("#institutionCombo1");
        robot.press(KeyCode.DOWN);
        robot.release(KeyCode.DOWN);
        robot.press(KeyCode.ENTER);
        robot.release(KeyCode.ENTER);

        robot.clickOn("#okButton3");


        robot.clickOn("#addPlaceButton");
        robot.clickOn("#okButtonPlace");

        // Forma nije validna i nece se zatvoriti!
        ime = robot.lookup("#nameOfPlaceField").queryAs(TextField.class);
        bg = ime.getBackground();
        colorFound = false;
        for (BackgroundFill bf : bg.getFills()) {
            if (bf.getFill().toString().contains(""))
                colorFound = true;
        }
        assertTrue(colorFound);

        //sada unosimo ispravne podatke
        robot.clickOn("#nameOfPlaceField");
        robot.write("Tuzla");
        robot.clickOn("#adressOfPlaceField");
        robot.write("Tuzla b.b.");
        robot.clickOn("#zipCodeField");
        robot.write("72000");
        robot.clickOn("#okButtonPlace");

        assertEquals(base.getPlaces().size(), base.getMaxIdFromPlaces()-1);
        //mjesto je sada dodano







        robot.clickOn("#okButton3");


        assertEquals(base.getAllChildren().size(), base.getMaxIdFromChildren()-1);


        robot.clickOn("#groupCombo");
        robot.press(KeyCode.DOWN);
        robot.release(KeyCode.DOWN);
        robot.press(KeyCode.ENTER);
        robot.release(KeyCode.ENTER);


        robot.press(KeyCode.CONTROL);
        robot.press(KeyCode.W);
        robot.release(KeyCode.CONTROL);
        robot.release(KeyCode.W);

        robot.clickOn("#addPerson");

        robot.clickOn("#okButtonn");
        robot.clickOn("#nameField");
        robot.write("Odgajatelj");
        robot.clickOn("#surenameField");
        robot.write("Odgajateljovic");
        robot.clickOn("#phoneNumberField");
        robot.write("061258963");
        robot.clickOn("#placeCombo");
        robot.press(KeyCode.DOWN);
        robot.release(KeyCode.DOWN);
        robot.press(KeyCode.ENTER);
        robot.release(KeyCode.ENTER);

        robot.clickOn("#institutionsCombo");
        robot.press(KeyCode.DOWN);
        robot.release(KeyCode.DOWN);
        robot.press(KeyCode.ENTER);
        robot.release(KeyCode.ENTER);

        robot.clickOn("#typeCombo");
        robot.press(KeyCode.DOWN);
        robot.release(KeyCode.DOWN);
        robot.press(KeyCode.ENTER);
        robot.release(KeyCode.ENTER);

        date = robot.lookup("#dateFieldd").queryAs(DatePicker.class);

        date.setValue(LocalDate.parse("02.02.1988", formatter));

        robot.clickOn("#jmbgField");
        robot.write("0202988153313");

        robot.clickOn("#emailField");
        robot.write("educator@gmail.com");

        robot.clickOn("#okButtonn");



        robot.clickOn("#institutionCombo");
        robot.press(KeyCode.DOWN);

        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
        robot.release(KeyCode.ALL_CANDIDATES);

        robot.press(KeyCode.CONTROL).press(KeyCode.C).release(KeyCode.CONTROL).release(KeyCode.C);
        robot.press(KeyCode.CONTROL).press(KeyCode.W).release(KeyCode.CONTROL).release(KeyCode.W);

        robot.release(KeyCode.ALL_CANDIDATES);

        robot.clickOn("#tableOfPersons");
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);



        robot.clickOn("#startButton");

        //ovdje vec testiramo for work...
        //pa cemo se kasnije vratiti da testiramo ostale opcije iz glavne...

        //ovo je moguce testirati samo ako je vrijeme od 7 do 18, jer je to radno vrijeme vrtica
        if (LocalDateTime.now().isAfter(LocalDateTime.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth(), 7, 0, 0)) && LocalDateTime.now().isBefore(LocalDateTime.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth(), 18, 0, 0))) {
            robot.clickOn("#listView");
            robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
            robot.clickOn("#apsentButton");
            robot.clickOn("#addComentButton");
            robot.clickOn("#comentArea");
            robot.write("Dodajemo komentar.");
            robot.clickOn("#okButton");
            robot.clickOn("#notApsentButton");
            robot.clickOn("#aboutButton");
            robot.clickOn("#okButton");
        } else{

        }

        robot.clickOn("#settingsButton");
        robot.release(KeyCode.ALL_CANDIDATES);



        base.removeInstitution(base.getMaxIdFromInstitutions()-1);
        base.removePlace(base.getMaxIdFromPlaces()-1);
        base.removePlace(base.getMaxIdFromPlaces()-1);
        base.removeChild1(base.getMaxIdFromChildren()-1, "Djete", "Djetovic");
        base.removeEducator1(base.getMaxIdFromEducators()-1);


    }

}
/*
    1. otvaramo glavni gui ok
    2. dodajemo ustanovu ok
    3. dodajemo po jedno djete u svaku kategoriju (u ovom koraku se testira i validacija)
    4. dodajemo odgajatelja (i validaciju testirati) ok
    5. dodajemo djete zatim ga brisemo
    6. dodajemo odgajatelja zatim ga brisemo
    7. mjenjamo dijete
    8. mjenjamo odgajatelja
    9. dodajemo djete zatim ga brisemo pomocu opcija
    10. dodajemo odgajatelja zatim ga brisemo pomocu opcija
    11. testiramo svu dugmada kada nista nije odabrano
    12. testiramo about
    13. testiramo promjenu jezika.
    14. testiramo izvjestaj
    15. prelazimo na forWork
    16. provjeravmo radno vrijeme
    17. provjeravmo dugmad kada nista nije selektovano
    18. provjeravom dugmad
    19. provjeravmo za prethodni datum
    20. provjeravmo za datum iz buducnosti
    21. prelazimo na bazu
    22. provjeravom exit
     */
/*
dobro... testovi fino funkcionisu kada je baza prazna...
sada moram rijesiti ovo sta ako u bazi vec postoje podaci...

ima jos stvari koje trebam testirati, izmjniti djete, obrisati djete, izmjeniti edukatora, izbrisati edukatora...
i ispitati ovo kada se pojavljuju ovi alerti...
 */