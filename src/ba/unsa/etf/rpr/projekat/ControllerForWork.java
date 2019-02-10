package ba.unsa.etf.rpr.projekat;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ControllerForWork {

    public ProgressBar progresBar;
    public Label timeLabel;
    public DatePicker datePicker;
    public Button apsentButton;
    public Button notApsentButton;
    public Button addComentButton;
    public Button aboutButton;
    public Button settingsButton;
    public Label educatorLabel;
    public Label institutionLabel;
    public Label groupLabel;
    public ListView listView;



    private KindergartenDAO base = new KindergartenDAO();

    Educator educator;
    Institution institution;
    String group;



    public ControllerForWork(String group, Educator educator, Institution institution){
        this.group = group;
        this.educator = educator;
        this.institution = institution;
    }



    public void initialize(){

        notApsentButton.setDisable(true);
        institutionLabel.setText(institution.getName());
        groupLabel.setText(group);
        educatorLabel.setText(educator.getName() + " " + educator.getSurename());

        if(group.equals("Grupa djece dobi od 1 do 2 godine"))
            listView.setItems(base.getChildren(this.institution, 1));
        else if(group.equals("Grupa djece dobi od 3 do 5 godina"))
            listView.setItems(base.getChildren(this.institution, 2));
        else if(group.equals("Grupa djece sa posbenim potrebama"))
            listView.setItems(base.getChildren(this.institution, 3));

        datePicker.setValue(LocalDate.now());


        listView.setCellFactory(new Callback<ListView<Child>, ListCell<Child>>() {
            @Override public ListCell<Child> call(ListView<Child> list) {
                return new MoneyFormatCell();
            }
        });


        settingsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader1 = new FXMLLoader(getClass().getClassLoader().getResource("fxml/main.fxml"));

                Parent root = null;
                try {
                    root = loader1.load();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                Stage stage = new Stage();
                stage.setTitle("Aplikacija za vrtić.");
                stage.setResizable(false);
                stage.setScene(new Scene(root, 744, 522));
                stage.show();
                Stage stage1 = (Stage) settingsButton.getScene().getWindow();
                stage1.close();
            }
        });




        DateFormat timeFormat = new SimpleDateFormat( "HH:mm:ss" );
        final Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis( 500 ), event -> {
                    timeLabel.setText( timeFormat.format( System.currentTimeMillis() ) );
                    if(LocalDateTime.now().getHour() >= 7 && LocalDateTime.now().getHour() <=18){
                        progresBar.setProgress(0.0909 * (LocalDateTime.now().getHour() - 7));
                    } else progresBar.setProgress(0);
                }));
        timeline.setCycleCount( Animation.INDEFINITE );
        timeline.play();

        apsentButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(listView.getSelectionModel().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Niste odabrali niti jedno djete");

                    alert.showAndWait();
                } else {


                    apsentButton.setDisable(true);
                    notApsentButton.setDisable(false);
                }
            }
        });
    }

    public class MoneyFormatCell extends ListCell<Child> {
        public MoneyFormatCell() {    }
        @Override protected void updateItem(Child item, boolean empty) {
            super.updateItem(item, empty);
            setText(item == null ? "" : String.valueOf(item.getId()) + " " + item.getName() + " " + item.getSurename());
            if (item != null) {
                //double value = item.doubleValue();
                setTextFill(item.isPrisutan() ? Color.GREEN : Color.RED);
            }
        }
    }
}