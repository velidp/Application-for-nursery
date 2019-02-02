package ba.unsa.etf.rpr.projekat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class KindergartenDAO {
    private Connection con;

    private PreparedStatement getInstitutions, placeByID, getPlaces, addPlace, getDirectors, directorById,
                                addInstitution, addDirector, getEducators, getSpecialEducators, getChildrenWithSpecialNeeds,
    getChildren12, getChildren35, getParents, removeChildren12, removeChildren35, removeChildrenWithSpecilaNeeds,
    removeEducator, removeSpecialEducator, removeParent;


    {
        try {
            con = DriverManager.getConnection("jdbc:sqlite:base.db");
            getInstitutions = con.prepareStatement("select * from institutions");
            getDirectors = con.prepareStatement("select * from directors");
            placeByID = con.prepareStatement("select * from places where id = ?");
            getPlaces = con.prepareStatement("select * from places");
            addPlace = con.prepareStatement("insert into places (id, name, adress, zipCode) values (?, ?, ?, ?)");
            directorById = con.prepareStatement("select * from directors where id = ?");
            addInstitution = con.prepareStatement("insert into institutions (id, place, director, name) values (?, ?, ?, ?)");
            addDirector = con.prepareStatement("insert into directors (id, name, surename, jmbg, date_of_birth, place) values (?, ?, ?, ?, ?, ?)");
            getEducators = con.prepareStatement("select * from educators");
            getSpecialEducators = con.prepareStatement("select * from specialEducators");
            getChildrenWithSpecialNeeds = con.prepareStatement("select * from childeren_with_special_needs");
            getChildren12 = con.prepareStatement("select * from children12");
            getChildren35 = con.prepareStatement("select * from children35");
            getParents = con.prepareStatement("select * from parents");
            removeChildren35 = con.prepareStatement("delete from children35 where id = ?");
            removeChildren12 = con.prepareStatement("delete from children12 where id = ?");
            removeChildrenWithSpecilaNeeds = con.prepareStatement("delete from childeren_with_special_needs where id = ?");
            removeEducator = con.prepareStatement("delete from educators where id = ?");
            removeSpecialEducator = con.prepareStatement("delete from specialEducators where id = ?");
            removeParent = con.prepareStatement("delete from parents where id = ?");
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

    public ObservableList<Person> getDirectors(){
        List<Person> list = new ArrayList<Person>();
        ObservableList<Person> observableList = FXCollections.observableList(list);

        ResultSet rs = null;
        try {
            rs = getDirectors.executeQuery();
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
                institution.setDirector(directorById(rs.getInt(3)));
                institution.setPlace(placeByID(rs.getInt(2)));
                institution.setName(rs.getString(4));
                list.add(institution);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return observableList;
    }

    private Person directorById(int idOfDirector) {
        Person director = new Person();
        try {
            directorById.setInt(1, idOfDirector);
            ResultSet rs = directorById.executeQuery();
            while (rs.next()){
                director.setId(idOfDirector);
                director.setName(rs.getString(2));
                director.setSurename(rs.getString(3));
                director.setJmbg(rs.getString(4));
                director.setDateOfBirth(rs.getDate(5).toLocalDate());
                director.setPlaceOfBirth(placeByID(rs.getInt(6)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return director;
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


    public void addInstitution(Institution institution) {
        try {
            addInstitution.setInt(1, this.getMaxIdFromInstitutions());
            addInstitution.setInt(2, institution.getPlace().getId());
            addInstitution.setInt(3, institution.getDirector().getId());
            addInstitution.setString(4, institution.getName());
            addInstitution.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getMaxIdFromDirecotors() {
        ArrayList lista = new ArrayList<Integer>();
        Iterator it = this.getDirectors().iterator();
        while (it.hasNext()){
            Person dircetro = (Person) it.next();
            lista.add(dircetro.getId());
        }
        if(!lista.isEmpty()) {
            return (int) Collections.max(lista) + 1;
        }
        return 1;
    }

    public void addDirector(Person director){
        try {
            addDirector.setInt(1, this.getMaxIdFromDirecotors());
            addDirector.setString(2, director.getName());
            addDirector.setString(3, director.getSurename());
            addDirector.setString(4, director.getJmbg());
            addDirector.setDate(5, Date.valueOf(director.getDateOfBirth()));
            addDirector.setInt(6, director.getPlaceOfBirth().getId());
            addDirector.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList getEducators() {
        List<Person> list = new ArrayList<Person>();
        ObservableList<Person> observableList = FXCollections.observableList(list);
        ResultSet rs = null;
        try {
            rs = getEducators.executeQuery();
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

    public ObservableList getSpecialEducators() {
        List<Person> list = new ArrayList<Person>();
        ObservableList<Person> observableList = FXCollections.observableList(list);
        ResultSet rs = null;
        try {
            rs = getSpecialEducators.executeQuery();
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

    public ObservableList getChildrenWithSpecialNeeds() {
        List<Person> list = new ArrayList<Person>();
        ObservableList<Person> observableList = FXCollections.observableList(list);
        ResultSet rs = null;
        try {
            rs = getChildrenWithSpecialNeeds.executeQuery();
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

    public ObservableList getChildren12() {
        List<Person> list = new ArrayList<Person>();
        ObservableList<Person> observableList = FXCollections.observableList(list);
        ResultSet rs = null;
        try {
            rs = getChildren12.executeQuery();
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

    public ObservableList getChildren35() {
        List<Person> list = new ArrayList<Person>();
        ObservableList<Person> observableList = FXCollections.observableList(list);
        ResultSet rs = null;
        try {
            rs = getChildren35.executeQuery();
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

    public ObservableList getParents() {
        List<Person> list = new ArrayList<Person>();
        ObservableList<Person> observableList = FXCollections.observableList(list);
        ResultSet rs = null;
        try {
            rs = getParents.executeQuery();
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

    public void removeEducator(Person educator){
        try {
            removeEducator.setInt(1, educator.getId());
            removeEducator.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    public void removeSpecialEducator(Person educator){
        try {
            removeSpecialEducator.setInt(1, educator.getId());
            removeSpecialEducator.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeChildren12(Person child){
        Child child1 = (Child) child;
        try {
            removeChildren12.setInt(1, child.getId());
            removeParent.setInt(1, child1.getParentt().getId());
            removeParent.executeUpdate();
            removeChildren12.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeChildren35(Person child){
        Child child1 = (Child) child;
        try {
            removeChildren35.setInt(1, child.getId());
            removeParent.setInt(1, child1.getParentt().getId());
            removeParent.executeUpdate();
            removeChildren35.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeChildrenWithSpecilaNeeds(Person child){
        Child child1 = (Child) child;
        try {
            removeChildrenWithSpecilaNeeds.setInt(1, child.getId());
            removeParent.setInt(1, child1.getParentt().getId());
            removeParent.executeUpdate();
            removeChildrenWithSpecilaNeeds.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
