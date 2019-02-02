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

public class AddDirectorController {

    public TextField nameField;
    public TextField surenameField;
    public TextField jmbgField;
    public DatePicker dateField;
    public ComboBox placeOfBirthCombo;
    public Button addPlaceButton;
    public Button okButton;
    public Button cancelButton;

    public KindergartenDAO base = new KindergartenDAO();

    public void initialize(){
        placeOfBirthCombo.setItems(base.getPlaces());

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
                placeOfBirthCombo.setItems(base.getPlaces());
                placeOfBirthCombo.getSelectionModel().selectLast();
            }
        });

        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Person person = new Person();

                //treba validirat

                person.setId(base.getMaxIdFromDirecotors());
                person.setName(nameField.getText().trim());
                person.setSurename(surenameField.getText().trim());
                person.setJmbg(jmbgField.getText().trim());
                person.setDateOfBirth(dateField.getValue());
                person.setPlaceOfBirth((Place)placeOfBirthCombo.getSelectionModel().getSelectedItem());

                base.addDirector(person);
                Stage stage = (Stage) cancelButton.getScene().getWindow();

                stage.close();
            }
        });
    }
}
