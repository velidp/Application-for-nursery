package ba.unsa.etf.rpr.projekat;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;

public class AddChildController {
    public Button okButton;
    public Button cancelButton;
    public TextField nameField;
    public TextField surenameField;
    public TextField jmbgField;
    public DatePicker dateField;
    public ComboBox placeCombo;
    public ComboBox institutionCombo;
    public TextField nameParentField;
    public TextField surenameParrentField;
    public TextField jmbgParentField;
    public DatePicker dateParrentField;
    public TextField phoneNumberField;
    public Button addPlaceButton;
    public KindergartenDAO base = new KindergartenDAO();
    public RadioButton specialNeedsNo;
    public RadioButton specialNeedsYes;




    public void initialize(){
        specialNeedsYes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(specialNeedsYes.isSelected()){
                    specialNeedsNo.setSelected(false);
                }
            }
        });

        specialNeedsNo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(specialNeedsNo.isSelected()){
                    specialNeedsYes.setSelected(false);
                }
            }
        });

        placeCombo.setItems(base.getPlaces());
        institutionCombo.setItems(base.getInstitutions());

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
                placeCombo.setItems(base.getPlaces());
                if(currentNumberOfInstitutions != base.getMaxIdFromPlaces()) {
                    placeCombo.getSelectionModel().selectLast();
                }
            }

        });

        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Child child = new Child();
                Parentt parentt = new Parentt();

                child.setName(nameField.getText().trim());
                child.setSurename(nameField.getText().trim());
                child.setJmbg(jmbgField.getText().trim());
                child.setDateOfBirth(dateField.getValue());
                child.setPlaceOfBirth((Place) placeCombo.getSelectionModel().getSelectedItem());
                child.setInstitution((Institution) institutionCombo.getSelectionModel().getSelectedItem());

                parentt.setName(nameParentField.getText().trim());
                parentt.setSurename(surenameParrentField.getText().trim());
                parentt.setJmbg(jmbgParentField.getText().trim());
                parentt.setDateOfBirth(dateParrentField.getValue());
                parentt.setPhoneNumber(phoneNumberField.getText());
                parentt.setId(base.getMaxIdFromParents());
                child.setParentt(parentt);
                child.setDjeteJeSaPosebnimPotrebama(specialNeedsYes.isSelected());

                base.addParrent(parentt);

                if (child.isDjeteJeSaPosebnimPotrebama()) {
                    child.setId(base.getMaxIdFromChildrenWithSpecialNeeds());
                    base.addChildWithSpecialNeeds(child);
                }

                if ((child.getDateOfBirth() != null) && (LocalDate.now() != null)) {
                    if(Period.between(child.getDateOfBirth(), LocalDate.now()).getYears() >= 1 && Period.between(child.getDateOfBirth(), LocalDate.now()).getYears() <= 2){
                        child.setId(base.getMaxIdFromChildren12());
                        base.addChild12(child);
                    }
                    if(Period.between(child.getDateOfBirth(), LocalDate.now()).getYears() >= 3 && Period.between(child.getDateOfBirth(), LocalDate.now()).getYears() <= 5){
                        child.setId(base.getMaxIdFromChildren35());
                        base.addChild35(child);
                    }
                }
                Stage stage = (Stage) okButton.getScene().getWindow();
                stage.close();
            }

        });
    }
}
