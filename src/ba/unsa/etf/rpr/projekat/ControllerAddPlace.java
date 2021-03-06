package ba.unsa.etf.rpr.projekat;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerAddPlace {

    public TextField nameOfPlaceField;
    public TextField adressOfPlaceField;
    public TextField zipCodeField;
    public Button okButtonPlace;
    public Button cancelButton;
    private KindergartenDAO base = new KindergartenDAO();

    public void initialize(){
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) cancelButton.getScene().getWindow();
                stage.close();
            }
        });

        okButtonPlace.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                boolean sveOk = validirajPrazno(nameOfPlaceField);
                sveOk &= validirajPrazno(zipCodeField);
                sveOk &= validirajPrazno(adressOfPlaceField);

                if(sveOk) {
                    Place place = new Place();
                    place.setId(base.getMaxIdFromPlaces());
                    place.setName(nameOfPlaceField.getText());
                    place.setAdress(adressOfPlaceField.getText());
                    place.setZipCode(zipCodeField.getText());
                    base.addPlace(place);
                    Stage stage = (Stage) okButtonPlace.getScene().getWindow();
                    stage.close();
                }
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
}