package ba.unsa.etf.rpr.projekat;

/*koje su bitne informacije za neku ustanovu*/

public class Institution {
    int id;
    Place place;
    Person director;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Person getDirector() {
        return director;
    }

    public void setDirector(Person director) {
        this.director = director;
    }

    @Override
    public String toString() {
        return "Mjesto: " + place.getName() + " Direktor: " + director.getName() + " " + director.getSurename() ;
    }
}
