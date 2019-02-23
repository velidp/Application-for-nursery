package ba.unsa.etf.rpr.projekat;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;

public class ControllerStatus {
    public Label nameLabel;
    public Label surenameLabel;
    public Label jmbgLabel;
    public Label memeberNumberLabel;
    public Label dateLabel;
    public Label arrivalLabel;
    public Label deparatureLabel;
    public TextArea commentsArea;
    public Button okButton;
    private Child child;
    private LocalDate date;

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");

    public ControllerStatus(Child child, LocalDate date){
        this.child = child;
        this.date = date;
    }


    public void initialize(){
        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) okButton.getScene().getWindow();
                stage.close();
            }
        });

        nameLabel.setText(child.getName());
        surenameLabel.setText(child.getSurename());
        jmbgLabel.setText(child.getJmbg());
        memeberNumberLabel.setText(String.valueOf(child.getId()));
        dateLabel.setText(date.toString());


        ArrayList<Day> days = child.desrialize();

        Iterator it = days.iterator();

        while (it.hasNext()){
            Day day = (Day) it.next();
            if(day.getDate().equals(date)){
                if(day.getArrivalTime() != null)
                arrivalLabel.setText(dtf.format(day.getArrivalTime()));
                if(day.getDepartureTime() != null)
                deparatureLabel.setText(dtf.format(day.getDepartureTime()));
                String str = "";
                Iterator it2 = day.getComments().iterator();
                while (it2.hasNext()){
                    str = str + (String) it2.next() + "\n";
                }
                commentsArea.setText(str);
            }
        }

    }
}
