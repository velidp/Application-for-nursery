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
import java.time.LocalDate;
import java.util.ResourceBundle;

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

    ResourceBundle bundle = ResourceBundle.getBundle("Trn");

    public void initialize(){
        placeOfInstitutionCombo.setItems(base.getPlaces());

        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) cancelButton.getScene().getWindow();
                stage.close();
            }
        });

        okButton.setOnAction(event -> {
            boolean sveOk = validirajPrazno(nameOfInstitution);
            sveOk &= validirajPrazno(nameOfDirectorField);
            sveOk &= validirajPrazno(surenameOfDirectorField);
            sveOk &= validirajPrazno(phoneNumberOfDirectorField);
            sveOk &= validirajPrazno(phoneNumberField);




            if(placeOfInstitutionCombo.getSelectionModel().isEmpty()){
                sveOk = false;
            }

            if (dateOfBirthOfDirectorField.getValue() == null || dateOfBirthOfDirectorField.getValue().isAfter(LocalDate.now())) {
                dateOfBirthOfDirectorField.getStyleClass().removeAll("poljeIspravno");
                dateOfBirthOfDirectorField.getStyleClass().add("poljeNijeIspravno");
                sveOk = false;
            } else {
                dateOfBirthOfDirectorField.getStyleClass().removeAll("poljeNijeIspravno");
                dateOfBirthOfDirectorField.getStyleClass().add("poljeIspravno");
            }

            if (!validateEmail(emailField.getText())) {
                emailField.getStyleClass().removeAll("poljeIspravno");
                emailField.getStyleClass().add("poljeNijeIspravno");
                sveOk = false;
            } else {
                emailField.getStyleClass().removeAll("poljeNijeIspravno");
                emailField.getStyleClass().add("poljeIspravno");
            }

            if (!validateEmail(emailOfDirectorField.getText())) {
                emailOfDirectorField.getStyleClass().removeAll("poljeIspravno");
                emailOfDirectorField.getStyleClass().add("poljeNijeIspravno");
                sveOk = false;
            } else {
                emailOfDirectorField.getStyleClass().removeAll("poljeNijeIspravno");
                emailOfDirectorField.getStyleClass().add("poljeIspravno");
            }

            /*
            if (!validateJmbgC(jmbgOfDirectorField.getText())) {
                jmbgOfDirectorField.getStyleClass().removeAll("poljeIspravno");
                jmbgOfDirectorField.getStyleClass().add("poljeNijeIspravno");
                sveOk = false;
            } else {
                jmbgOfDirectorField.getStyleClass().removeAll("poljeNijeIspravno");
                jmbgOfDirectorField.getStyleClass().add("poljeIspravno");
            }*/


            if(sveOk) {
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
                FXMLLoader loader1 = new FXMLLoader(getClass().getClassLoader().getResource("fxml/addPlace.fxml"), bundle);
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

    private boolean validirajPrazno(TextField polje) {
        if (polje.getText().trim().isEmpty()) {
            polje.getStyleClass().removeAll("poljeIspravno");
            polje.getStyleClass().add("poljeNijeIspravno");
            return false;
        } else {
            polje.getStyleClass().removeAll("poljeNijeIspravno");
            polje.getStyleClass().add("poljeIspravno");
        }
        return true;
    }

    private boolean validateEmail(String email){
        String regex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

        if(email.matches(regex)) return true;
        return false;
    }

    private boolean validateJmbgC(String jmbg) {
        if (jmbg.length() != 13)
            return false;

        LocalDate datum = dateOfBirthOfDirectorField.getValue();
        if (datum.getDayOfMonth() != Integer.parseInt(jmbg.substring(0, 2)))
            return false;
        else if (datum.getMonthValue() != Integer.parseInt(jmbg.substring(2, 4)))
            return false;

        int godina = Integer.parseInt(jmbg.substring(4, 7));
        if (godina > 500) godina += 1000; else godina += 2000;
        if (datum.getYear() != godina)
            return false;

        int[] br = new int[13];
        for (int i=0; i<13; i++) {
            br[i] = jmbg.charAt(i);
            br[i] -= 48;
        }
        int sum = 7*(br[0]+br[6]) + 6*(br[1]+br[7]) + 5*(br[2]+br[8]) + 4*(br[3]+br[9]) + 3*(br[4]+br[10]) + 2*(br[5]+br[11]);
        sum = 11 - (sum % 11);
        if (sum > 9) sum = 0;
        if (br[12] != sum) return false;
        return true;
    }


}