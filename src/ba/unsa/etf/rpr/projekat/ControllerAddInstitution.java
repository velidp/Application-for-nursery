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

public class ControllerAddInstitution {

    public Button okButton;
    public Button cancelButton;
    public ComboBox placeOfInstitutionCombo;
    public Button addPlaceOfInstitutionButton;
    public TextField nameOfInstitution;
    public DatePicker dateOfBirthOfDirectorField;
    public TextField nameOfDirectorField;
    public TextField surenameOfDirectorField;
    public TextField jmbgOfDirectorField;
    public TextField phoneNumberField;
    public TextField emailField;
    public TextField phoneNumberOfDirectorField;
    public TextField emailOfDirectorField;

    KindergartenDAO base = new KindergartenDAO();

    public void initialize(){
        placeOfInstitutionCombo.setItems(base.getPlaces());

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
                Institution institution = new Institution();
                institution.setId(base.getMaxIdFromInstitutions());
                institution.setName(nameOfInstitution.getText());
                institution.setPlace((Place) placeOfInstitutionCombo.getSelectionModel().getSelectedItem());
                institution.setPhoneNumber(phoneNumberField.getText());
                institution.setEmail(emailField.getText());
                institution.setNameOfDirector(nameOfDirectorField.getText());
                institution.setSurenameOfDirector(surenameOfDirectorField.getText());
                institution.setJmbgOfDirector(jmbgOfDirectorField.getText());
                institution.setDateOfBirthOfDirector(dateOfBirthOfDirectorField.getValue());
                institution.setPhoneNumber(phoneNumberOfDirectorField.getText());
                institution.setEmailOfDirector(emailOfDirectorField.getText());

                base.addInstitiution(institution);
                Stage stage = (Stage) okButton.getScene().getWindow();
                stage.close();
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
                stage.showAndWait();
                placeOfInstitutionCombo.setItems(base.getPlaces());
                placeOfInstitutionCombo.getSelectionModel().selectLast();

            }
        });
    }
}