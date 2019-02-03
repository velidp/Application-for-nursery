package ba.unsa.etf.rpr.projekat;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class AddEducatorController {

    public TextField nameField;
    public TextField surenameField;
    public TextField jmbgField;
    public DatePicker dateField;
    public ComboBox institutionsCombo;
    public ComboBox typeCombo;
    public Button okButton;
    public Button cancelButton;

    public Person person = null;

    public AddEducatorController(Person person) {
        this.person = person;
    }

    public KindergartenDAO base = new KindergartenDAO();


    public void initialize(){
        institutionsCombo.setItems(base.getInstitutions());
        typeCombo.getItems().addAll("Obični odgajatelj", "Odgajatelj za djecu sa posebnim potrebama");

        if(person != null){
            nameField.setText(person.getName());
            surenameField.setText(person.getSurename());
            jmbgField.setText(person.getJmbg());
            dateField.setValue(person.getDateOfBirth());
            institutionsCombo.getSelectionModel().select(person.getInstitution());
            if(person.isType())
            typeCombo.getSelectionModel().select("Odgajatelj za djecu sa posebnim potrebama");
            else typeCombo.getSelectionModel().select("Obični odgajatelj");
        }

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
                if(person == null) {
                    Educator educator = new Educator();

                    educator.setName(nameField.getText().trim());
                    educator.setSurename(surenameField.getText().trim());
                    educator.setJmbg(jmbgField.getText().trim());
                    educator.setDateOfBirth(dateField.getValue());
                    educator.setInstitution((Institution) institutionsCombo.getSelectionModel().getSelectedItem());

                    if (!typeCombo.getSelectionModel().isEmpty() && typeCombo.getSelectionModel().getSelectedItem().equals("Obični odgajatelj")) {
                        educator.setType(false);
                        educator.setId(base.getMaxIdFromEducators());
                        base.addEducator(educator);
                    }
                    if (!typeCombo.getSelectionModel().isEmpty() && typeCombo.getSelectionModel().getSelectedItem().equals("Odgajatelj za djecu sa posebnim potrebama")) {
                        educator.setType(true);
                        educator.setId(base.getMaxIdFromSpecialEducators());
                        base.addSpecialEducator(educator);
                    }

                    File file = new File(educator.getName() + String.valueOf(educator.getId()) + ".xml");
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Stage stage = (Stage) okButton.getScene().getWindow();
                    stage.close();
                } else {
                    if(!person.isType())
                        base.removeEducator(person);
                    else if (person.isType())
                        base.removeSpecialEducator(person);

                    Educator educator = new Educator();

                    educator.setName(nameField.getText().trim());
                    educator.setSurename(surenameField.getText().trim());
                    educator.setJmbg(jmbgField.getText().trim());
                    educator.setDateOfBirth(dateField.getValue());
                    educator.setInstitution((Institution) institutionsCombo.getSelectionModel().getSelectedItem());

                    if (!typeCombo.getSelectionModel().isEmpty() && typeCombo.getSelectionModel().getSelectedItem().equals("Obični odgajatelj")) {
                        educator.setType(false);
                        educator.setId(base.getMaxIdFromEducators());
                        base.addEducator(educator);
                    }
                    if (!typeCombo.getSelectionModel().isEmpty() && typeCombo.getSelectionModel().getSelectedItem().equals("Odgajatelj za djecu sa posebnim potrebama")) {
                        educator.setType(true);
                        educator.setId(base.getMaxIdFromSpecialEducators());
                        base.addSpecialEducator(educator);
                    }
                    File file = new File(educator.getName() + String.valueOf(educator.getId()) + ".xml");
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Stage stage = (Stage) okButton.getScene().getWindow();
                    stage.close();
                }
            }
        });
    }
}
