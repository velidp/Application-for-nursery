package ba.unsa.etf.rpr.projekat;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Day implements Serializable {
    LocalDate date;
    boolean apsent;
    LocalDateTime arrivalTime;
    LocalDateTime departureTime;
    ArrayList comments = new ArrayList<String>();

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isApsent() {
        return apsent;
    }

    public void setApsent(boolean apsent) {
        this.apsent = apsent;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public ArrayList getComments() {
        return comments;
    }

    public void setComments(ArrayList comments) {
        this.comments = comments;
    }

    public String toString(){
        return "DaDatum: " + date;
    }
}
