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
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    public TableView tableOfChildren;
    public TableColumn memberNumberColumn;
    public TableColumn nameColumn;
    public TableColumn surenameColumn;
    public TableColumn presenceColumn;

    private KindergartenDAO base = new KindergartenDAO();

    String group, educator, institution;
    ObservableList list;

    public ControllerForWork(String group, String educator, String institution, ObservableList list){
        this.group = group;
        this.educator = educator;
        this.institution = institution;
        this.list = list;
    }



    public void initialize(){

        institutionLabel.setText(institution);
        groupLabel.setText(group);
        educatorLabel.setText(educator);
        tableOfChildren.setItems(list);
        datePicker.setValue(LocalDate.now());


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
                }));
        timeline.setCycleCount( Animation.INDEFINITE );
        timeline.play();


    }
}