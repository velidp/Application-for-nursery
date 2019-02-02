package ba.unsa.etf.rpr.projekat;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;

public class AddInstitutionController {

    public Button okButton;
    public Button cancelButton;
    public ComboBox placeOfInstitutionCombo;
    public Button addPlaceOfInstitutionButton;
    public ComboBox directorCombo;
    public Button addDirectorButton;


    public KindergartenDAO base = new KindergartenDAO();



    public void updatePlaces(){
        placeOfInstitutionCombo.setItems(base.getPlaces());
        directorCombo.setItems(base.getDirectors());
    }

    public void initialize(){
        updatePlaces();

        addDirectorButton.setOnAction(new EventHandler<ActionEvent>() {
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
                stage.setTitle("Dodaj direktora");
                stage.setResizable(false);
                stage.setScene(new Scene(root, 396, 197));
                int currentNumberOfDirectors = base.getMaxIdFromDirecotors();
                stage.showAndWait();
                directorCombo.setItems(base.getDirectors());
                if(currentNumberOfDirectors != base.getMaxIdFromDirecotors()) {
                    directorCombo.getSelectionModel().selectLast();
                }
            }
        });

        addPlaceOfInstitutionButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader1 = new FXMLLoader(getClass().getClassLoader().getResource("fxml/addPlace.fxml"));

                Parent root = null;
                try {
                    root = loader1.load();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                Stage stage = new Stage();
                stage.setTitle("Dodaj mjesto");
                stage.setResizable(false);
                stage.setScene(new Scene(root, 304, 159));
                int currentNumberOfInstitutions = base.getMaxIdFromPlaces();
                stage.showAndWait();
                placeOfInstitutionCombo.setItems(base.getPlaces());
                if(currentNumberOfInstitutions != base.getMaxIdFromPlaces()) {
                    placeOfInstitutionCombo.getSelectionModel().selectLast();
                }
            }
        });

        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) cancelButton.getScene().getWindow();
                stage.close();
            }
        });

        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Person director = new Person();

                director = (Person) directorCombo.getSelectionModel().getSelectedItem();

                Institution institution = new Institution();
                institution.setId(base.getMaxIdFromInstitutions());
                institution.setPlace((Place)placeOfInstitutionCombo.getSelectionModel().getSelectedItem());
                institution.setDirector((director));
                base.addInstitution(institution);
                Stage stage = (Stage) cancelButton.getScene().getWindow();
                stage.close();
            }
        });
    }
}
