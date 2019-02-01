package ba.unsa.etf.rpr.projekat;

import java.time.LocalDate;
import java.util.ArrayList;

public class Educator extends Person{
    ArrayList<LocalDate> arrivals = new ArrayList<LocalDate>();
    ArrayList<LocalDate> departures = new ArrayList<>();
    ArrayList<String> notes = new ArrayList<String>();

    public ArrayList<LocalDate> getArrivals() {
        return arrivals;
    }

    public void setArrivals(ArrayList<LocalDate> arrivals) {
        this.arrivals = arrivals;
    }

    public ArrayList<LocalDate> getDepartures() {
        return departures;
    }

    public void setDepartures(ArrayList<LocalDate> departures) {
        this.departures = departures;
    }

    public ArrayList<String> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<String> notes) {
        this.notes = notes;
    }
}
