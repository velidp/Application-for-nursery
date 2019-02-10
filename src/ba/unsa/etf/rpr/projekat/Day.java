package ba.unsa.etf.rpr.projekat;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Day implements Serializable {
    LocalDate date;
    boolean apsent;
    LocalDateTime arrivalTime;
    LocalDateTime departureTime;
    ArrayList<String> comments = new ArrayList<String>();

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

    public ArrayList<String> getComments() {
        return comments;
    }

    public void setComments(ArrayList<String> comments) {
        this.comments = comments;
    }

    public String toString(){
        String str = "Datum: " + date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        if(apsent) str = str + "\nPrisutan: Da";
        else if (!apsent) str = str + "\nPrisutan: Ne";
        str = str + " \nVrijeme dolaska: " + arrivalTime + "\nVrijeme odlaska: " + departureTime + comments;

        return str;
    }
}
