package ba.unsa.etf.rpr.projekat;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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

    private Child child;

    public ControllerAddEducator(Child child){
        this.child = child;
    }

    public void initialize(){
        institutionsCombo.setItems(base.getInstitutions());
        placeCombo.setItems(base.getPlaces());
        typeCombo.getItems().addAll("Obični odgajatelj", "Odgajatelj za djecu sa posebnim potrebama");

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
                Educator educator = new Educator();

                educator.setId(base.getMaxIdFromEducators());
                educator.setName(nameField.getText());
                educator.setSurename(surenameField.getText());
                educator.setJmbg(jmbgField.getText());
                educator.setDateOfBirth(dateField.getValue());
                educator.setDweling((Place) placeCombo.getSelectionModel().getSelectedItem());
                educator.setInstitution((Institution) institutionsCombo.getSelectionModel().getSelectedItem());
                if(typeCombo.getSelectionModel().getSelectedItem() == "Odgajatelj za djecu sa posebnim potrebama")
                    educator.setEducatorForChildrenForSpecialNeeds(true);
                else if (typeCombo.getSelectionModel().getSelectedItem() == "Obični odgajatelj")
                    educator.setEducatorForChildrenForSpecialNeeds(false);

                base.addEducator(educator);

                Stage stage = (Stage) okButton.getScene().getWindow();
                stage.close();
            }
        });

    }
}