package ba.unsa.etf.rpr.projekat;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddEducatorController {

    public TextField nameField;
    public TextField surenameField;
    public TextField jmbgField;
    public DatePicker dateField;
    public ComboBox institutionsCombo;
    public ComboBox typeCombo;

    public KindergartenDAO base = new KindergartenDAO();
    public Button okButton;
    public Button cancelButton;

    public void initialize(){
        institutionsCombo.setItems(base.getInstitutions());
        typeCombo.getItems().addAll("Obični odgajatelj", "Odgajatelj za djecu sa posebnim potrebama");

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
                Educator educator = new Educator();

                educator.setName(nameField.getText().trim());
                educator.setSurename(surenameField.getText().trim());
                educator.setJmbg(jmbgField.getText().trim());
                educator.setDateOfBirth(dateField.getValue());
                educator.setInstitution((Institution) institutionsCombo.getSelectionModel().getSelectedItem());

                if(!typeCombo.getSelectionModel().isEmpty() && typeCombo.getSelectionModel().getSelectedItem().equals("Obični odgajatelj")){
                    educator.setZaDjecuSaPosebnimPotrebama(false);
                    educator.setId(base.getMaxIdFromEducators());
                    base.addEducator(educator);
                }
                if(!typeCombo.getSelectionModel().isEmpty() && typeCombo.getSelectionModel().getSelectedItem().equals("Odgajatelj za djecu sa posebnim potrebama")){
                    educator.setZaDjecuSaPosebnimPotrebama(true);
                    educator.setId(base.getMaxIdFromSpecialEducators());
                    base.addSpecialEducator(educator);
                }

                Stage stage = (Stage) okButton.getScene().getWindow();
                stage.close();
            }
        });
    }
}
