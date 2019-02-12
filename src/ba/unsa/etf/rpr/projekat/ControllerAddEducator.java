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

public class ControllerAddEducator {


    public Button okButton;
    public Button cancelButton;
    public TextField nameField;
    public TextField surenameField;
    public TextField jmbgField;
    public DatePicker dateField;
    public ComboBox institutionsCombo;
    public ComboBox typeCombo;
    public ComboBox placeCombo;
    public Button addPlaceButton;
    private KindergartenDAO base = new KindergartenDAO();
    ResourceBundle bundle = ResourceBundle.getBundle("Trn");

    private Educator educatoR;

    public ControllerAddEducator(Educator educator){
        this.educatoR = educator;
    }

    public void initialize(){
        institutionsCombo.setItems(base.getInstitutions());
        placeCombo.setItems(base.getPlaces());
        typeCombo.getItems().addAll(bundle.getString("ordinary"), bundle.getString("educatorSn"));

        if(educatoR != null){
            nameField.setText(educatoR.getName());
            surenameField.setText(educatoR.getSurename());
            jmbgField.setText(educatoR.getSurename());
            dateField.setValue(educatoR.getDateOfBirth());
            institutionsCombo.getSelectionModel().select(educatoR.getInstitution());
            if(educatoR.isEducatorForChildrenForSpecialNeeds())
            typeCombo.getSelectionModel().select(0);
            else if (educatoR.isEducatorForChildrenForSpecialNeeds())
                typeCombo.getSelectionModel().select(1);
            placeCombo.getSelectionModel().select(educatoR.getDweling());
        }

        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) cancelButton.getScene().getWindow();
                stage.close();
            }
        });

        addPlaceButton.setOnAction(new EventHandler<ActionEvent>() {
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
                stage.setTitle(bundle.getString("addPlace"));
                stage.setResizable(false);
                stage.setScene(new Scene(root, 304, 159));
                stage.showAndWait();
                placeCombo.setItems(base.getPlaces());
                placeCombo.getSelectionModel().selectLast();
            }
        });

        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(educatoR == null) {

                    boolean sveOk = validirajPrazno(nameField);
                    sveOk &= validirajPrazno(surenameField);


                    if (placeCombo.getSelectionModel().isEmpty()) {
                        placeCombo.getStyleClass().removeAll("poljeIspravno");
                        placeCombo.getStyleClass().add("poljeNijeIspravno");
                        sveOk = false;
                    } else {
                        placeCombo.getStyleClass().removeAll("poljeNijeIspravno");
                        placeCombo.getStyleClass().add("poljeIspravno");
                    }

                    if (institutionsCombo.getSelectionModel().isEmpty()) {
                        institutionsCombo.getStyleClass().removeAll("poljeIspravno");
                        institutionsCombo.getStyleClass().add("poljeNijeIspravno");
                        sveOk = false;
                    } else {
                        institutionsCombo.getStyleClass().removeAll("poljeNijeIspravno");
                        institutionsCombo.getStyleClass().add("poljeIspravno");
                    }

                    if (typeCombo.getSelectionModel().isEmpty()) {
                        typeCombo.getStyleClass().removeAll("poljeIspravno");
                        typeCombo.getStyleClass().add("poljeNijeIspravno");
                        sveOk = false;
                    } else {
                        typeCombo.getStyleClass().removeAll("poljeNijeIspravno");
                        typeCombo.getStyleClass().add("poljeIspravno");
                    }

                    if (dateField.getValue() == null || dateField.getValue().isAfter(LocalDate.now())) {
                        dateField.getStyleClass().removeAll("poljeIspravno");
                        dateField.getStyleClass().add("poljeNijeIspravno");
                        sveOk = false;
                    } else {
                        dateField.getStyleClass().removeAll("poljeNijeIspravno");
                        dateField.getStyleClass().add("poljeIspravno");
                    }

                    /*
                    if (!validateJmbgP(jmbgField.getText())) {
                        jmbgField.getStyleClass().removeAll("poljeIspravno");
                        jmbgField.getStyleClass().add("poljeNijeIspravno");
                        sveOk = false;
                    } else {
                        jmbgField.getStyleClass().removeAll("poljeNijeIspravno");
                        jmbgField.getStyleClass().add("poljeIspravno");
                    }*/


                    if(sveOk) {
                        Educator educator = new Educator(nameField.getText(), surenameField.getText(), base.getMaxIdFromEducators());

                        educator.setJmbg(jmbgField.getText());
                        educator.setDateOfBirth(dateField.getValue());
                        educator.setDweling((Place) placeCombo.getSelectionModel().getSelectedItem());
                        educator.setInstitution((Institution) institutionsCombo.getSelectionModel().getSelectedItem());
                        if (typeCombo.getSelectionModel().getSelectedItem() == bundle.getString("educatorSn"))
                            educator.setEducatorForChildrenForSpecialNeeds(true);
                        else if (typeCombo.getSelectionModel().getSelectedItem() == bundle.getString("ordinary"))
                            educator.setEducatorForChildrenForSpecialNeeds(false);

                        base.addEducator(educator);

                        Stage stage = (Stage) okButton.getScene().getWindow();
                        stage.close();
                    }

                } else if (educatoR != null){
                    base.removeEducator(educatoR);

                    boolean sveOk = validirajPrazno(nameField);
                    sveOk &= validirajPrazno(surenameField);


                    if (placeCombo.getSelectionModel().isEmpty()) {
                        placeCombo.getStyleClass().removeAll("poljeIspravno");
                        placeCombo.getStyleClass().add("poljeNijeIspravno");
                        sveOk = false;
                    } else {
                        placeCombo.getStyleClass().removeAll("poljeNijeIspravno");
                        placeCombo.getStyleClass().add("poljeIspravno");
                    }

                    if (institutionsCombo.getSelectionModel().isEmpty()) {
                        institutionsCombo.getStyleClass().removeAll("poljeIspravno");
                        institutionsCombo.getStyleClass().add("poljeNijeIspravno");
                        sveOk = false;
                    } else {
                        institutionsCombo.getStyleClass().removeAll("poljeNijeIspravno");
                        institutionsCombo.getStyleClass().add("poljeIspravno");
                    }

                    if (typeCombo.getSelectionModel().isEmpty()) {
                        typeCombo.getStyleClass().removeAll("poljeIspravno");
                        typeCombo.getStyleClass().add("poljeNijeIspravno");
                        sveOk = false;
                    } else {
                        typeCombo.getStyleClass().removeAll("poljeNijeIspravno");
                        typeCombo.getStyleClass().add("poljeIspravno");
                    }

                    if (dateField.getValue() == null || dateField.getValue().isAfter(LocalDate.now())) {
                        dateField.getStyleClass().removeAll("poljeIspravno");
                        dateField.getStyleClass().add("poljeNijeIspravno");
                        sveOk = false;
                    } else {
                        dateField.getStyleClass().removeAll("poljeNijeIspravno");
                        dateField.getStyleClass().add("poljeIspravno");
                    }

                    /*
                    if (!validateJmbgP(jmbgField.getText())) {
                        jmbgField.getStyleClass().removeAll("poljeIspravno");
                        jmbgField.getStyleClass().add("poljeNijeIspravno");
                        sveOk = false;
                    } else {
                        jmbgField.getStyleClass().removeAll("poljeNijeIspravno");
                        jmbgField.getStyleClass().add("poljeIspravno");
                    }*/



                    if(sveOk) {
                        Educator educator = new Educator(nameField.getText(), surenameField.getText(), base.getMaxIdFromEducators());

                        educator.setJmbg(jmbgField.getText());
                        educator.setDateOfBirth(dateField.getValue());
                        educator.setDweling((Place) placeCombo.getSelectionModel().getSelectedItem());
                        educator.setInstitution((Institution) institutionsCombo.getSelectionModel().getSelectedItem());
                        if (typeCombo.getSelectionModel().getSelectedItem() == bundle.getString("educatorSn"))
                            educator.setEducatorForChildrenForSpecialNeeds(true);
                        else if (typeCombo.getSelectionModel().getSelectedItem() == bundle.getString("ordinary"))
                            educator.setEducatorForChildrenForSpecialNeeds(false);

                        base.addEducator(educator);

                        Stage stage = (Stage) okButton.getScene().getWindow();
                        stage.close();
                    }
                }
            }
        });

    }

    private boolean validateJmbgP(String jmbg) {
        if (jmbg.length() != 13)
            return false;

        LocalDate datum = dateField.getValue();
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
}