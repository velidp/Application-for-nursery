package ba.unsa.etf.rpr.projekat;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import static javafx.application.Application.STYLESHEET_CASPIAN;
import static javafx.application.Application.setUserAgentStylesheet;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class ControllerAddChildTest {


    @Start
    public void start (Stage stage) throws Exception {
        setUserAgentStylesheet(STYLESHEET_CASPIAN);
        Locale.setDefault(new Locale("bs", "BA"));
        ResourceBundle bundle = ResourceBundle.getBundle("Trn");
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"), bundle);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage primaryStage = new Stage();
        primaryStage.setTitle(bundle.getString("appName"));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("img/child_1191970.png"));
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }




    @Test
    void initialize(FxRobot robot) {
        robot.clickOn("#addPerson");
        robot.clickOn("#cancelButton");
    }
}