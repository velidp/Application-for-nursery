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

    private Educator educatoR;

    public ControllerAddEducator(Educator educator){
        this.educatoR = educator;
    }

    public void initialize(){
        institutionsCombo.setItems(base.getInstitutions());
        placeCombo.setItems(base.getPlaces());
        typeCombo.getItems().addAll("Obični odgajatelj", "Odgajatelj za djecu sa posebnim potrebama");

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

        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(educatoR == null) {
                    Educator educator = new Educator();

                    educator.setId(base.getMaxIdFromEducators());
                    educator.setName(nameField.getText());
                    educator.setSurename(surenameField.getText());
                    educator.setJmbg(jmbgField.getText());
                    educator.setDateOfBirth(dateField.getValue());
                    educator.setDweling((Place) placeCombo.getSelectionModel().getSelectedItem());
                    educator.setInstitution((Institution) institutionsCombo.getSelectionModel().getSelectedItem());
                    if (typeCombo.getSelectionModel().getSelectedItem() == "Odgajatelj za djecu sa posebnim potrebama")
                        educator.setEducatorForChildrenForSpecialNeeds(true);
                    else if (typeCombo.getSelectionModel().getSelectedItem() == "Obični odgajatelj")
                        educator.setEducatorForChildrenForSpecialNeeds(false);

                    base.addEducator(educator);

                    Stage stage = (Stage) okButton.getScene().getWindow();
                    stage.close();
                } else if (educatoR != null){
                    Educator educator = new Educator();
                    base.removeEducator(educatoR.getId());

                    educator.setId(base.getMaxIdFromEducators());
                    educator.setName(nameField.getText());
                    educator.setSurename(surenameField.getText());
                    educator.setJmbg(jmbgField.getText());
                    educator.setDateOfBirth(dateField.getValue());
                    educator.setDweling((Place) placeCombo.getSelectionModel().getSelectedItem());
                    educator.setInstitution((Institution) institutionsCombo.getSelectionModel().getSelectedItem());
                    if (typeCombo.getSelectionModel().getSelectedItem() == "Odgajatelj za djecu sa posebnim potrebama")
                        educator.setEducatorForChildrenForSpecialNeeds(true);
                    else if (typeCombo.getSelectionModel().getSelectedItem() == "Obični odgajatelj")
                        educator.setEducatorForChildrenForSpecialNeeds(false);

                    base.addEducator(educator);

                    Stage stage = (Stage) okButton.getScene().getWindow();
                    stage.close();
                }
            }
        });

    }
}