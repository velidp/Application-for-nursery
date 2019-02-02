package ba.unsa.etf.rpr.projekat;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class MainController {

    public MenuItem closeMenu;
    public MenuItem removeMenu;
    public MenuItem addMenu;
    public MenuItem reportMenu;
    public MenuItem aboutMenu;
    public TableView tableOfPersons;
    public TableColumn imeColumn;
    public TableColumn prezimeColumn;
    public TableColumn jmbgColumn;
    public ComboBox groupCombo;
    public ComboBox categoryCombo;
    public Button addPerson;
    public Button removePerson;
    public Button editPerson;
    public Button reportButton;
    public Button startButton;
    public ComboBox institutionCombo;
    public Button addInstitutionButton;

    public KindergartenDAO base = new KindergartenDAO();


    public void initialize(){
        groupCombo.getItems().addAll("Grupa djece dobi od 1 do 2 godine", "Grupa djece dobi od 3 do 5 godina", "Grupa djece sa posebnim potremabma");
        categoryCombo.getItems().addAll("Uposlenici", "Djeca");

        institutionCombo.setItems(base.getInstitutions());


        editPerson.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader1 = new FXMLLoader(getClass().getClassLoader().getResource("fxml/addDirector.fxml"));

                Parent root = null;
                try {
                    root = loader1.load();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                Stage stage = new Stage();
                stage.setTitle("Izmjeni osobu");
                stage.setResizable(false);
                stage.setScene(new Scene(root, 396, 197));
                stage.showAndWait();
            }
        });


        addPerson.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader1 = new FXMLLoader(getClass().getClassLoader().getResource("fxml/addDirector.fxml"));

                Parent root = null;
                try {
                    root = loader1.load();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                Stage stage = new Stage();
                stage.setTitle("Dodaj osobu");
                stage.setResizable(false);
                stage.setScene(new Scene(root, 396, 197));
                stage.showAndWait();
            }
        });

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader1 = new FXMLLoader(getClass().getClassLoader().getResource("fxml/forWork.fxml"));

                Parent root = null;
                try {
                    root = loader1.load();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                Stage stage = new Stage();
                stage.setTitle("Dodaj osobu");
                stage.setResizable(false);
                stage.setScene(new Scene(root, 757, 415));
                stage.showAndWait();
            }
        });

        removePerson.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Brisanje osobe");
                alert.setHeaderText("");
                alert.setContentText("Da li zaista želite izbrisati odabranu osobu?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    // ... user chose OK
                } else {
                    // ... user chose CANCEL or closed the dialog
                }
            }
        });

        removeMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Brisanje osobe");
                alert.setHeaderText("");
                alert.setContentText("Da li zaista želite izbrisati odabranu osobu?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    // ... user chose OK
                } else {
                    // ... user chose CANCEL or closed the dialog
                }
            }
        });

        addMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader1 = new FXMLLoader(getClass().getClassLoader().getResource("fxml/addDirector.fxml"));

                Parent root = null;
                try {
                    root = loader1.load();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                Stage stage = new Stage();
                stage.setTitle("Dodaj osobu");
                stage.setResizable(false);
                stage.setScene(new Scene(root, 396, 197));
                stage.showAndWait();
            }
        });

        closeMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
            }
        });

        aboutMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText("Razvoj Programskih Rješenja (2018/2019) Projekat");
                alert.setContentText("Aplikacija za vrtiić\nAutor: Velid Poško\nBroj indeksa: 17823");
                alert.showAndWait();
            }
        });

        addInstitutionButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader1 = new FXMLLoader(getClass().getClassLoader().getResource("fxml/addInstitution.fxml"));

                Parent root = null;
                try {
                    root = loader1.load();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                Stage stage = new Stage();
                stage.setTitle("Dodaj ustanovu");
                stage.setResizable(false);
                stage.setScene(new Scene(root, 379, 157));
                stage.showAndWait();
                institutionCombo.setItems(base.getInstitutions());
                institutionCombo.getSelectionModel().selectLast();
            }
        });
    }
}
