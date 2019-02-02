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
    removeEducator, removeSpecialEducator, removeParent, addChildWithSpecialNeeds, addChild12, addChild35, addParrent,
    addEducator, addSpecialEducator;


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
            addChildWithSpecialNeeds = con.prepareStatement("insert into childeren_with_special_needs (id, name, surename, jmbg, date_of_birth, place, parent, institution) values (?, ?,?,?,?,?,?,?)");
            addChild12 = con.prepareStatement("insert into children12 (id, name, surename, jmbg, date_of_birth, place, parent, institution) values (?, ?,?,?,?,?,?,?)");
            addEducator = con.prepareStatement("insert into educators (id, name, surename, jmbg, date_of_birth, institution, type) values (?, ?, ?, ?, ?, ?, ?)");
            addSpecialEducator = con.prepareStatement("insert into specialEducators (id, name, surename, jmbg, date_of_birth, institution, type) values (?, ?, ?, ?, ?, ?, ?)");
            addParrent = con.prepareStatement("insert into parents (id, name,surename, jmbg, date_of_birth, phone_number) values(?,?,?,?,?,?)");
            addChild35 = con.prepareStatement("insert into children35 (id, name, surename, jmbg, date_of_birth, place, parent, institution) values (?, ?,?,?,?,?,?,?)");
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
        try {
            removeChildren12.setInt(1, child.getId());
            removeParent.setInt(1, this.getIdOfParent(child));
            removeParent.executeUpdate();
            removeChildren12.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getIdOfParent(Person child) {
        ResultSet rs = null;
        try {
            rs = getChildren12.executeQuery();
            while (rs.next()){
                if(child.getId() == rs.getInt(1)){
                    return rs.getInt(7);
                }
            }
            rs = getChildren35.executeQuery();
            while (rs.next()){
                if(child.getId() == rs.getInt(1)){
                    return rs.getInt(7);
                }
            }
            rs = getChildrenWithSpecialNeeds.executeQuery();
            while (rs.next()){
                if(child.getId() == rs.getInt(1)){
                    return rs.getInt(7);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;

    }

    public void removeChildren35(Person child){
        try {
            removeChildren35.setInt(1, child.getId());
            removeParent.setInt(1, this.getIdOfParent(child));
            removeParent.executeUpdate();
            removeChildren35.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeChildrenWithSpecilaNeeds(Person child){

        try {
            removeChildrenWithSpecilaNeeds.setInt(1, child.getId());
            removeParent.setInt(1, this.getIdOfParent(child));
            removeParent.executeUpdate();
            removeChildrenWithSpecilaNeeds.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getMaxIdFromParents() {
        ArrayList lista = new ArrayList<Integer>();
        Iterator it = this.getParents().iterator();
        while (it.hasNext()){
            Person parent = (Person) it.next();
            lista.add(parent.getId());
        }
        if(!lista.isEmpty()) {
            return (int) Collections.max(lista) + 1;
        }
        return 1;
    }

    public int getMaxIdFromChildrenWithSpecialNeeds() {
        ArrayList lista = new ArrayList<Integer>();
        Iterator it = this.getChildrenWithSpecialNeeds().iterator();
        while (it.hasNext()){
            Person child = (Person) it.next();
            lista.add(child.getId());
        }
        if(!lista.isEmpty()) {
            return (int) Collections.max(lista) + 1;
        }
        return 1;
    }

    public void addChildWithSpecialNeeds(Child child) {
        try {
            addChildWithSpecialNeeds.setInt(1, child.getId());
            addChildWithSpecialNeeds.setString(2, child.getName());
            addChildWithSpecialNeeds.setString(3, child.getSurename());
            addChildWithSpecialNeeds.setString(4, child.getJmbg());
            addChildWithSpecialNeeds.setDate(5, Date.valueOf(child.getDateOfBirth()));
            addChildWithSpecialNeeds.setInt(6, child.getPlaceOfBirth().getId());
            addChildWithSpecialNeeds.setInt(7, child.getParentt().getId());
            addChildWithSpecialNeeds.setInt(8, child.getInstitution().getId());
            addChildWithSpecialNeeds.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getMaxIdFromChildren12() {
        ArrayList lista = new ArrayList<Integer>();
        Iterator it = this.getChildren12().iterator();
        while (it.hasNext()){
            Person child = (Person) it.next();
            lista.add(child.getId());
        }
        if(!lista.isEmpty()) {
            return (int) Collections.max(lista) + 1;
        }
        return 1;
    }

    public void addChild12(Child child) {
        try {
            addChild12.setInt(1, child.getId());
            addChild12.setString(2, child.getName());
            addChild12.setString(3, child.getSurename());
            addChild12.setString(4, child.getJmbg());
            addChild12.setDate(5, Date.valueOf(child.getDateOfBirth()));
            addChild12.setInt(6, child.getPlaceOfBirth().getId());
            addChild12.setInt(7, child.getParentt().getId());
            addChild12.setInt(8, child.getInstitution().getId());
            addChild12.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getMaxIdFromChildren35() {
        ArrayList lista = new ArrayList<Integer>();
        Iterator it = this.getChildren35().iterator();
        while (it.hasNext()){
            Person child = (Person) it.next();
            lista.add(child.getId());
        }
        if(!lista.isEmpty()) {
            return (int) Collections.max(lista) + 1;
        }
        return 1;
    }

    public void addChild35(Child child) {
        try {
            addChild35.setInt(1, child.getId());
            addChild35.setString(2, child.getName());
            addChild35.setString(3, child.getSurename());
            addChild35.setString(4, child.getJmbg());
            addChild35.setDate(5, Date.valueOf(child.getDateOfBirth()));
            addChild35.setInt(6, child.getPlaceOfBirth().getId());
            addChild35.setInt(7, child.getParentt().getId());
            addChild35.setInt(8, child.getInstitution().getId());
            addChild35.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addParrent(Parentt parentt) {
        try {
            addParrent.setInt(1, parentt.getId());
            addParrent.setString(2, parentt.getName());
            addParrent.setString(3, parentt.getSurename());
            addParrent.setString(4, parentt.getJmbg());
            addParrent.setDate(5, Date.valueOf(parentt.getDateOfBirth()));
            addParrent.setString(6, parentt.getPhoneNumber());
            addParrent.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public int getMaxIdFromEducators() {
        ArrayList lista = new ArrayList<Integer>();
        Iterator it = this.getEducators().iterator();
        while (it.hasNext()){
            Person child = (Person) it.next();
            lista.add(child.getId());
        }
        if(!lista.isEmpty()) {
            return (int) Collections.max(lista) + 1;
        }
        return 1;
    }

    public int getMaxIdFromSpecialEducators() {
        ArrayList lista = new ArrayList<Integer>();
        Iterator it = this.getSpecialEducators().iterator();
        while (it.hasNext()){
            Person child = (Person) it.next();
            lista.add(child.getId());
        }
        if(!lista.isEmpty()) {
            return (int) Collections.max(lista) + 1;
        }
        return 1;
    }

    public void addEducator(Educator educator) {
        try {
            addEducator.setInt(1, educator.getId());
            addEducator.setString(2, educator.getName());
            addEducator.setString(3, educator.getSurename());
            addEducator.setString(4, educator.getJmbg());
            addEducator.setDate(5, Date.valueOf(educator.getDateOfBirth()));
            addEducator.setInt(6, educator.getInstitution().getId());
            addEducator.setInt(7, 0);
            addEducator.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void addSpecialEducator(Educator educator) {
        try {
            addSpecialEducator.setInt(1, educator.getId());
            addSpecialEducator.setString(2, educator.getName());
            addSpecialEducator.setString(3, educator.getSurename());
            addSpecialEducator.setString(4, educator.getJmbg());
            addSpecialEducator.setDate(5, Date.valueOf(educator.getDateOfBirth()));
            addSpecialEducator.setInt(6, educator.getInstitution().getId());
            addSpecialEducator.setInt(7, 1);
            addSpecialEducator.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
