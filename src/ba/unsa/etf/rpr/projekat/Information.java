package ba.unsa.etf.rpr.projekat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Information {
    LocalDate date;
    ArrayList<LocalDateTime> arrivals = new ArrayList<>();
    ArrayList<LocalDateTime> departures = new ArrayList<>();
    ArrayList<String> coments = new ArrayList<>();

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ArrayList<LocalDateTime> getArrivals() {
        return arrivals;
    }

    public void setArrivals(ArrayList<LocalDateTime> arrivals) {
        this.arrivals = arrivals;
    }

    public ArrayList<LocalDateTime> getDepartures() {
        return departures;
    }

    public void setDepartures(ArrayList<LocalDateTime> departures) {
        this.departures = departures;
    }

    public ArrayList<String> getComents() {
        return coments;
    }

    public void setComents(ArrayList<String> coments) {
        this.coments = coments;
    }
}
