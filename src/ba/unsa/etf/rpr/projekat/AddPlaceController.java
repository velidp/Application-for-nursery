package ba.unsa.etf.rpr.projekat;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddPlaceController {

    public TextField nameOfPlaceField;
    public TextField adressOfPlaceField;
    public TextField zipCodeField;
    public Button okButton;
    public Button cancelButton;


    public KindergartenDAO base = new KindergartenDAO();

    public void initialize(){

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
                //ovdje treba validirati sva polja...


                Place place = new Place();
                place.setId(base.getMaxIdFromPlaces());
                place.setName(nameOfPlaceField.getText().trim());
                place.setAdress(adressOfPlaceField.getText().trim());
                place.setZipCode(zipCodeField.getText().trim());
                base.addPlace(place);
                Stage stage = (Stage) okButton.getScene().getWindow();
                stage.close();
            }
        });
    }
}