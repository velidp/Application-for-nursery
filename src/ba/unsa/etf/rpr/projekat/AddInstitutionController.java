package ba.unsa.etf.rpr.projekat;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddInstitutionController {

    public Button okButton;
    public Button cancelButton;
    public ComboBox placeOfInstitutionCombo;
    public TextField nameOfDirectorField;
    public TextField surenameOfDirectorField;
    public TextField jmbgOfDirectorField;
    public DatePicker dateOfBirthOfDirector;
    public ComboBox placeOfBirthOfDirectorCombo;
    public Button addPlaceOfInstitutionButton;
    public Button addPlaceOfBirthOfDirectorButton;

    public KindergartenDAO base = new KindergartenDAO();

    public void updatePlaces(){
        placeOfBirthOfDirectorCombo.setItems(base.getPlaces());
        placeOfInstitutionCombo.setItems(base.getPlaces());
    }

    public void initialize(){
        updatePlaces();

        addPlaceOfBirthOfDirectorButton.setOnAction(new EventHandler<ActionEvent>() {
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
                int currentNumberOfPlaces = base.getMaxIdFromPlaces();
                stage.showAndWait();
                updatePlaces();
                if(currentNumberOfPlaces != base.getMaxIdFromPlaces()) {
                    placeOfBirthOfDirectorCombo.getSelectionModel().select(base.getPlaces().get(base.getMaxIdFromPlaces() - 2));
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
                int currentNumberOfPlaces = base.getMaxIdFromPlaces();
                stage.showAndWait();
                updatePlaces();
                if(currentNumberOfPlaces != base.getMaxIdFromPlaces()) {
                    placeOfInstitutionCombo.getSelectionModel().select(base.getPlaces().get(base.getMaxIdFromPlaces() - 2));
                }
            }
        });

        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) okButton.getScene().getWindow();
                stage.close();
            }
        });

        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Person director = new Person();

                director.setName(nameOfDirectorField.getText().trim());
                director.setSurename(surenameOfDirectorField.getText().trim());
                director.setId(base.getMaxIdFromPersons());
                director.setJmbg(jmbgOfDirectorField.getText().trim());
                director.setDateOfBirth(dateOfBirthOfDirector.getValue());
                director.setPlaceOfBirth((Place)placeOfBirthOfDirectorCombo.getSelectionModel().getSelectedItem());

                Institution institution = new Institution();
                institution.setId(base.getMaxIdFromInstitutions());
                institution.setPlace((Place)placeOfInstitutionCombo.getSelectionModel().getSelectedItem());
                institution.setDirector((director));
            }
        });
    }
}
