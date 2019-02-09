package ba.unsa.etf.rpr.projekat;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerAddChild {


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
    public RadioButton specialNeedsNo;
    public RadioButton specialNeedsYes;

    private KindergartenDAO base = new KindergartenDAO();
    public Child chilD;

    public ControllerAddChild(Child child){
        this.chilD = child;
    }

    public void initialize(){

        institutionCombo.setItems(base.getInstitutions());

        placeCombo.setItems(base.getPlaces());

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
                stage.showAndWait();
                placeCombo.setItems(base.getPlaces());
                placeCombo.getSelectionModel().selectLast();
            }
        });

        specialNeedsYes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(specialNeedsYes.isSelected()) specialNeedsNo.setSelected(false);
            }
        });

        specialNeedsNo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(specialNeedsNo.isSelected()) specialNeedsYes.setSelected(false);
            }
        });

        if(chilD != null){
            nameField.setText(chilD.getName());
            surenameField.setText(chilD.getSurename());
            jmbgField.setText(chilD.getJmbg());
            dateField.setValue(chilD.getDateOfBirth());
            placeCombo.getSelectionModel().select(chilD.getDwelling());
            institutionCombo.getSelectionModel().select(chilD.getInstitution());
            nameParentField.setText(chilD.getParentsName());
            surenameParrentField.setText(chilD.getParentsSurename());
            jmbgParentField.setText(chilD.getJmbg());
            dateParrentField.setValue(chilD.getParentsDateOfBirth());
            phoneNumberField.setText(chilD.getPhoneNumber());
            if(chilD.isChildWithSpecialNeeds()) specialNeedsYes.setSelected(true);
            else if(!chilD.isChildWithSpecialNeeds()) specialNeedsNo.setSelected(true);

        }

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
                if(chilD == null){
                    Child child = new Child();

                    child.setId(base.getMaxIdFromChildren());
                    child.setName(nameField.getText());
                    child.setSurename(surenameField.getText());
                    child.setDateOfBirth(dateField.getValue());
                    child.setJmbg(jmbgField.getText());
                    child.setDwelling((Place)placeCombo.getSelectionModel().getSelectedItem());
                    child.setInstitution((Institution) institutionCombo.getSelectionModel().getSelectedItem());
                    child.setChildWithSpecialNeeds(specialNeedsYes.isSelected());
                    child.setParentsName(nameParentField.getText());
                    child.setParentsSurename(surenameParrentField.getText());
                    child.setParentsJmbg(jmbgParentField.getText());
                    child.setParentsDateOfBirth(dateParrentField.getValue());
                    child.setPhoneNumber(phoneNumberField.getText());

                    base.addChild(child);

                    Stage stage = (Stage) okButton.getScene().getWindow();
                    stage.close();

                } else {
                    Child child = new Child();

                    base.removeChild(chilD.getId());

                    child.setId(base.getMaxIdFromChildren());
                    child.setName(nameField.getText());
                    child.setSurename(surenameField.getText());
                    child.setDateOfBirth(dateField.getValue());
                    child.setJmbg(jmbgField.getText());
                    child.setDwelling((Place)placeCombo.getSelectionModel().getSelectedItem());
                    child.setInstitution((Institution) institutionCombo.getSelectionModel().getSelectedItem());
                    child.setChildWithSpecialNeeds(specialNeedsYes.isSelected());
                    child.setParentsName(nameParentField.getText());
                    child.setParentsSurename(surenameParrentField.getText());
                    child.setParentsJmbg(jmbgParentField.getText());
                    child.setParentsDateOfBirth(dateParrentField.getValue());
                    child.setPhoneNumber(phoneNumberField.getText());

                    base.addChild(child);

                    Stage stage = (Stage) okButton.getScene().getWindow();
                    stage.close();
                }
            }
        });

    }
}