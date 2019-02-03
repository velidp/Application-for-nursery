package ba.unsa.etf.rpr.projekat;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

public class ForWorkController {

    public ProgressBar progresBar;
    public Label timeLabel;
    public DatePicker datePicker;
    public Button apsentButton;
    public Button notApsentButton;
    public Button addComentButton;
    public Button aboutButton;
    public Button settingsButton;
    public Label educatorLabel;
    public Label groupLabel;
    public Label institutionLabel;
    public TableColumn memberNumberColumn;
    public TableColumn nameColumn;
    public TableColumn surenameColumn;
    public TableColumn apsentColumn;
    public TableView tableOfPersons;




    public String educator, group, institution;
    KindergartenDAO base = new KindergartenDAO();

    public ForWorkController(String educator, String group, String institution){
        this.educator = educator;
        this.group = group;
        this. institution = institution;
    }

    public void initialize(){
        educatorLabel.setText(educator);
        groupLabel.setText(group);
        institutionLabel.setText(institution);
        datePicker.setValue(LocalDate.now());


        memberNumberColumn.setCellValueFactory(new PropertyValueFactory("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
        surenameColumn.setCellValueFactory(new PropertyValueFactory("surename"));
        //ovo je kritično
        //apsentColumn.setCellValueFactory(new PropertyValueFactory("da"));

        if(groupLabel.getText() == "Grupa djece dobi od 1 do 2 godine"){
            tableOfPersons.setItems(base.getChildren12());
        } else if(groupLabel.getText() == "Grupa djece dobi od 3 do 5 godina"){
            tableOfPersons.setItems(base.getChildren35());
        } else if (groupLabel.getText() == "Grupa djece sa posebnim potremabma"){
            tableOfPersons.setItems(base.getChildrenWithSpecialNeeds());
        }

        if(LocalDateTime.now().getHour() < 18 && LocalDateTime.now().getHour() >= 7){
            Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
                timeLabel.setText(LocalDateTime.now().getHour() + ":" + (LocalDateTime.now().getMinute()) + ":" + LocalDateTime.now().getSecond());
                if(LocalDateTime.now().getHour() >= 7 && LocalDateTime.now().getHour() <= 18)
                progresBar.setProgress(0.091*(LocalDateTime.now().getHour()-7));

            }),
                    new KeyFrame(Duration.seconds(1))
            );
            clock.setCycleCount(Animation.INDEFINITE);
            clock.play();
        } else if(LocalDateTime.now().getHour() >= 18){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Ingormacija");
                alert.setContentText("Radno vrijeme je završeno");
                alert.showAndWait();
        } else if(LocalDateTime.now().getHour() < 7){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ingormacija");
            alert.setContentText("Početak radnog vremena je u 7:00h.");
            alert.showAndWait();
        }

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
                stage.setTitle("Aplikacija za vrtić");
                stage.setResizable(false);
                stage.setScene(new Scene(root, 744, 522));
                stage.show();
                Stage stage1 = (Stage) settingsButton.getScene().getWindow();
                stage1.close();
            }
        });

        apsentButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //apsentColumn.get
            }
        });

        notApsentButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
    }
}
