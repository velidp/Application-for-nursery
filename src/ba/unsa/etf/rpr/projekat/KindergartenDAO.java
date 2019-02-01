package ba.unsa.etf.rpr.projekat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class KindergartenDAO {
    private Connection con;

    private PreparedStatement getInstitutions, personByID, placeByID, getPlaces, addPlace, getPersons;


    {
        try {
            con = DriverManager.getConnection("jdbc:sqlite:base.db");
            getInstitutions = con.prepareStatement("select * from institutions");
            personByID = con.prepareStatement("select * from persons where id = ?");
            placeByID = con.prepareStatement("select * from places where id = ?");
            getPlaces = con.prepareStatement("select * from places");
            addPlace = con.prepareStatement("insert into places (id, name, adress, zipCode) values (?, ?, ?, ?)");
            getPersons = con.prepareStatement("select * from persons");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Place> getPlaces(){
        List<Place> list = new ArrayList<Place>();
        ObservableList<Place> observableList = FXCollections.observableList(list);

        ResultSet rs = null;
        try{
            rs = getPlaces.executeQuery();
            while (rs.next()){
                Place place = new Place();
                place.setId(rs.getInt(1));
                place.setName(rs.getString(2));
                place.setAdress(rs.getString(3));
                place.setZipCode(rs.getString(4));
                list.add(place);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return observableList;
    }

    public ObservableList<Person> getPersons(){
        List<Person> list = new ArrayList<Person>();
        ObservableList<Person> observableList = FXCollections.observableList(list);

        ResultSet rs = null;
        try {
            rs = getPersons.executeQuery();
            while(rs.next()){
                Person person = new Person();
                person.setId(rs.getInt(1));
                person.setName(rs.getString(2));
                person.setSurename(rs.getString(3));
                person.setJmbg(rs.getString(4));
                person.setDateOfBirth(rs.getDate(5).toLocalDate());
                person.setPlaceOfBirth(placeByID(rs.getInt(6)));
                list.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return observableList;
    }


    public ObservableList<Institution> getInstitutions(){
        List<Institution> list = new ArrayList<Institution>();
        ObservableList<Institution> observableList = FXCollections.observableList(list);

        ResultSet rs = null;
        try {
            rs = getInstitutions.executeQuery();
            while(rs.next()){
                Institution institution = new Institution();
                institution.setId(rs.getInt(1));
                institution.setDirector(personById(rs.getInt(3)));
                institution.setPlace(placeByID(rs.getInt(2)));
                list.add(institution);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return observableList;
    }

    public Person personById(int idOfPerson){
        Person person = new Person();
        try {
            personByID.setInt(1, idOfPerson);
            ResultSet rs = personByID.executeQuery();
            while (rs.next()){
                person.setId(rs.getInt(1));
                person.setName(rs.getString(2));
                person.setSurename(rs.getString(3));
                person.setJmbg(rs.getString(4));
                person.setDateOfBirth(rs.getDate(5).toLocalDate());
                person.setPlaceOfBirth(placeByID(rs.getInt(6)));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

    private Place placeByID(int idOfPlace) {
        Place place = new Place();

        try {
            placeByID.setInt(1, idOfPlace);
            ResultSet rs = placeByID.executeQuery();
            while (rs.next()){
                place.setId(rs.getInt(1));
                place.setName(rs.getString(2));
                place.setAdress(rs.getString(3));
                place.setZipCode(rs.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return place;
    }

    public int getMaxIdFromPlaces(){
            ArrayList lista = new ArrayList<Integer>();
            Iterator it = this.getPlaces().iterator();
            while (it.hasNext()){
                Place place = (Place) it.next();
                lista.add(place.getId());
            }
            if(!lista.isEmpty()) {
                return (int) Collections.max(lista) + 1;
            }
            return 1;
    }

    public void addPlace(Place place) {
        try {
            addPlace.setInt(1, this.getMaxIdFromPlaces());
            addPlace.setString(2, place.getName());
            addPlace.setString(3, place.getAdress());
            addPlace.setString(4, place.getZipCode());
            addPlace.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getMaxIdFromInstitutions() {
        ArrayList lista = new ArrayList<Integer>();
        Iterator it = this.getInstitutions().iterator();
        while (it.hasNext()){
            Institution institution = (Institution) it.next();
            lista.add(institution.getId());
        }
        if(!lista.isEmpty()) {
            return (int) Collections.max(lista) + 1;
        }
        return 1;
    }


    public int getMaxIdFromPersons() {
        ArrayList lista = new ArrayList<Integer>();
        Iterator it = this.getPersons().iterator();
        while (it.hasNext()){
            Person person = (Person) it.next();
            lista.add(person.getId());
        }
        if(!lista.isEmpty()) {
            return (int) Collections.max(lista) + 1;
        }
        return 1;
    }


}
