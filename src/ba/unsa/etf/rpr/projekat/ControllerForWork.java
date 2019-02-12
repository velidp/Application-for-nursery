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
import javafx.stage.Stage;
import javafx.util.Duration;
import org.bouncycastle.math.ec.ScaleYPointMap;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

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


    ResourceBundle bundle = ResourceBundle.getBundle("Trn");

    private KindergartenDAO base = new KindergartenDAO();

    Educator educator;
    Institution institution;
    String group;



    public ControllerForWork(String group, Educator educator, Institution institution){
        this.group = group;
        this.educator = educator;
        this.institution = institution;
    }



    public void initialize() {
        Day day = new Day();

        day.setApsent(false);
        day.setDate(LocalDate.now());
        day.setDepartureTime(null);
        day.setArrivalTime(null);
        day.setComments(new ArrayList<String>());


        //radno otvorena prvi put u toku radnog vremena...

        institutionLabel.setText(institution.getName());
        groupLabel.setText(group);
        educatorLabel.setText(educator.getName() + " " + educator.getSurename());

        if (group.equals(bundle.getString("Grupa12")))
            listView.setItems(base.getChildren(this.institution, 1));
        else if (group.equals(bundle.getString("Grupa35")))
            listView.setItems(base.getChildren(this.institution, 2));
        else if (group.equals(bundle.getString("GrupaSN")))
            listView.setItems(base.getChildren(this.institution, 3));

        datePicker.setValue(LocalDate.now());

        datePicker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(datePicker.getValue().isAfter(LocalDate.now())){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Alert");
                    alert.setHeaderText(null);
                    alert.setContentText(bundle.getString("FututreDate"));
                    alert.showAndWait();
                    datePicker.setValue(LocalDate.now());
                }
                if (!LocalDate.now().equals(datePicker.getValue())) {
                    apsentButton.setDisable(true);
                    notApsentButton.setDisable(true);
                    addComentButton.setDisable(true);
                } else if (LocalDate.now().equals(datePicker.getValue())) {
                    apsentButton.setDisable(false);
                    addComentButton.setDisable(false);
                    notApsentButton.setDisable(false);
                }
            }
        });

        settingsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader1 = new FXMLLoader(getClass().getClassLoader().getResource("fxml/main.fxml"), bundle);

                Parent root = null;
                try {
                    root = loader1.load();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                Stage stage = new Stage();
                stage.setTitle(bundle.getString("appName"));
                stage.setResizable(false);
                stage.setScene(new Scene(root, 744, 522));
                stage.show();
                Stage stage1 = (Stage) settingsButton.getScene().getWindow();
                stage1.close();
            }
        });


        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        final Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(500), event -> {
                    timeLabel.setText(timeFormat.format(System.currentTimeMillis()));
                    if (LocalDateTime.now().getHour() >= 7 && LocalDateTime.now().getHour() <= 18) {
                        progresBar.setProgress(0.0909 * (LocalDateTime.now().getHour() - 7));
                    } else {
                        progresBar.setProgress(0);
                        apsentButton.setDisable(true);
                        notApsentButton.setDisable(true);
                        addComentButton.setDisable(true);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Alert");
                        alert.setHeaderText(null);
                        alert.setContentText(bundle.getString("worktime"));

                        alert.showAndWait();
                    }
                }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();


        Iterator it = listView.getItems().iterator();
        while (it.hasNext()){
            Child childTemp = (Child) it.next();
            ArrayList<Day> daysTemp = childTemp.desrialize();
            if(!daysTemp.get(daysTemp.size()-1).getDate().equals(LocalDate.now())) {
                System.out.println("Prvi put...");
                daysTemp.add(day);
            } else {
                System.out.println("Vec ste ulazili...");
            }

            childTemp.serialize(daysTemp);

        }


        apsentButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (listView.getSelectionModel().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Alert");
                    alert.setHeaderText(null);
                    alert.setContentText(bundle.getString("noChild"));

                    alert.showAndWait();
                } else if(((Child)listView.getSelectionModel().getSelectedItem()).desrialize().get(((Child)listView.getSelectionModel().getSelectedItem()).desrialize().size()-1).isApsent()){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Alert");
                    alert.setHeaderText(null);
                    alert.setContentText(bundle.getString("apsentChild"));

                    alert.showAndWait();
                } else {

                    ArrayList<Day> days = ((Child) listView.getSelectionModel().getSelectedItem()).desrialize();
                    Day day = days.get(days.size() - 1);
                    day.setApsent(true);
                    if (day.getArrivalTime() != null) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Alert");
                        alert.setHeaderText(null);
                        alert.setContentText("vec ste koristili ovo dugme danas");

                        alert.showAndWait();
                    } else {

                        day.setArrivalTime(LocalDateTime.now());
                        days.remove(days.size() - 1);
                        days.add(day);

                        ((Child) listView.getSelectionModel().getSelectedItem()).serialize(days);

                        listView.setCellFactory(param -> new ListCell<Child>() {
                            @Override
                            protected void updateItem(Child item, boolean empty) {
                                super.updateItem(item, empty);

                                if (empty || item == null) {
                                    setText(null);
                                    setStyle(null);
                                } else {
                                    setText(item.getId() + " " + item.getName() + " " + item.getSurename());
                                    setStyle(item.desrialize().get(item.desrialize().size() - 1).isApsent() ? "-fx-control-inner-background: greenyellow;" : "-fx-control-inner-background: lightpink;");
                                }
                            }
                        });
                    }
                }
            }

        });

        notApsentButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (listView.getSelectionModel().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Informacija");
                    alert.setHeaderText(null);
                    alert.setContentText(bundle.getString("noChild"));

                    alert.showAndWait();
                } else if(!((Child)listView.getSelectionModel().getSelectedItem()).desrialize().get(((Child)listView.getSelectionModel().getSelectedItem()).desrialize().size()-1).isApsent()){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Informacija");
                    alert.setHeaderText(null);
                    alert.setContentText(bundle.getString("noapsentChild"));

                    alert.showAndWait();
                } else {

                    ArrayList<Day> days = ((Child) listView.getSelectionModel().getSelectedItem()).desrialize();
                    Day day = days.get(days.size() - 1);
                    day.setApsent(true);

                    if (day.getDepartureTime() != null) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Alert");
                        alert.setHeaderText(null);
                        alert.setContentText("vec ste koristili ovo dugme danas");

                        alert.showAndWait();
                    } else {

                        day.setDepartureTime(LocalDateTime.now());
                        days.remove(days.size() - 1);
                        days.add(day);

                        ((Child) listView.getSelectionModel().getSelectedItem()).serialize(days);

                        listView.setCellFactory(param -> new ListCell<Child>() {
                            @Override
                            protected void updateItem(Child item, boolean empty) {
                                super.updateItem(item, empty);

                                if (empty || item == null) {
                                    setText(null);
                                    setStyle(null);
                                } else {
                                    setText(item.getId() + " " + item.getName() + " " + item.getSurename());
                                    setStyle(item.desrialize().get(item.desrialize().size() - 1).isApsent() ? "-fx-control-inner-background: greenyellow;" : "-fx-control-inner-background: lightpink;");
                                }
                            }
                        });

                        days = ((Child) listView.getSelectionModel().getSelectedItem()).desrialize();
                        day = days.get(days.size() - 1);
                        day.setApsent(false);
                        day.setDepartureTime(LocalDateTime.now());
                        days.remove(days.size() - 1);
                        days.add(day);

                        ((Child) listView.getSelectionModel().getSelectedItem()).serialize(days);
                    }
                }
            }
        });

        addComentButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (listView.getSelectionModel().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Informacija");
                    alert.setHeaderText(null);
                    alert.setContentText(bundle.getString("noChild"));

                    alert.showAndWait();
                } else if(!((Child)listView.getSelectionModel().getSelectedItem()).desrialize().get(((Child)listView.getSelectionModel().getSelectedItem()).desrialize().size()-1).isApsent()){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Alert");
                    alert.setHeaderText(null);
                    alert.setContentText(bundle.getString("noComent"));

                    alert.showAndWait();
                } else {
                    FXMLLoader loader1 = new FXMLLoader(getClass().getClassLoader().getResource("fxml/AddComent.fxml"),bundle);
                    loader1.setController(new ControllerAddComment((Child)listView.getSelectionModel().getSelectedItem()));
                    Parent root = null;
                    try {
                        root = loader1.load();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    Stage stage = new Stage();
                    stage.setTitle(bundle.getString("addComent"));
                    stage.setResizable(false);
                    stage.setScene(new Scene(root, 407, 236));
                    stage.show();
                }
            }
        });


        aboutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (listView.getSelectionModel().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Alert");
                    alert.setHeaderText(null);
                    alert.setContentText(bundle.getString("noChild"));

                    alert.showAndWait();
                } else {
                    FXMLLoader loader1 = new FXMLLoader(getClass().getClassLoader().getResource("fxml/Status.fxml"),bundle);
                    loader1.setController(new ControllerStatus((Child) listView.getSelectionModel().getSelectedItem(), datePicker.getValue()));
                    Parent root = null;
                    try {
                        root = loader1.load();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    Stage stage = new Stage();
                    stage.setTitle("Status");
                    stage.setResizable(false);
                    stage.setScene(new Scene(root, 398, 359));
                    stage.show();
                }
            }
        });

    }
}