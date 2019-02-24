package ba.unsa.etf.rpr.projekat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class KindergartenDAO {

    private Connection con;

    private PreparedStatement getChildren, removePlace, removeInstitution, removeChild, removeEducator, addEducator, getEducators, getInstitutions, addChild, placeById, addInstitution, getPlaces, addPlace, institutionById;

    {
        try {
            con = DriverManager.getConnection("jdbc:sqlite:base.db");
            getChildren = con.prepareStatement("select * from children order by id");
            getInstitutions = con.prepareStatement("select * from institutions");
            placeById = con.prepareStatement("select * from places where id = ?");
            addInstitution = con.prepareStatement("insert into institutions (id, name, place, phone_number, email, name_of_director, surename_of_director, jmbg_of_director, date_of_birth_of_director, phone_number_of_director, email_of_director) values (?,?,?,?,?,?,?,?,?,?,?)");
            getPlaces = con.prepareStatement("select * from places");
            addPlace = con.prepareStatement("insert into places (id, name, adress, zip_code) values (?,?,?,?)");
            institutionById = con.prepareStatement("select * from institutions where id = ?");
            addEducator = con.prepareStatement("insert into educators (id,name,surename,jmbg,date_of_birth,place,institution,special_needs, email, phone_number) values (?,?,?,?,?,?,?,?,?,?)");
            addChild = con.prepareStatement("insert into children (id,name,surename,date_of_birth,jmbg,place,institution,special_needs,parents_name,parents_surename, parents_jmbg,parents_date_of_birth,parents_phone_number) values (?,?,?,?,?,?,?,?,?,?,?,?,?)");
            getEducators = con.prepareStatement("select * from educators");
            removeEducator = con.prepareStatement("delete from educators where id = ?");
            removeInstitution = con.prepareStatement("delete from institutions where id = ?");
            removeChild = con.prepareStatement("delete from children where id = ?");
            removePlace = con.prepareStatement("delete from places where id = ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Institution> getInstitutions() {
        List<Institution> list = new ArrayList<Institution>();
        ObservableList<Institution> observableList = FXCollections.observableList(list);
        ResultSet rs = null;
        try {
            rs = getInstitutions.executeQuery();
            while(rs.next()){
                Institution institution = new Institution();
                institution.setId(rs.getInt(1));
                institution.setName(rs.getString(2));
                institution.setPlace(this.placeById(rs.getInt(3)));
                institution.setPhoneNumber(rs.getString(4));
                institution.setEmail(rs.getString(5));
                institution.setNameOfDirector(rs.getString(6));
                institution.setSurenameOfDirector(rs.getString(7));
                institution.setJmbgOfDirector(rs.getString(8));
                institution.setDateOfBirthOfDirector(rs.getDate(9).toLocalDate());
                institution.setPhoneNumberOfDirector(rs.getString(10));
                institution.setEmailOfDirector(rs.getString(11));
                list.add(institution);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return observableList;
    }

    public Place placeById(int anInt) {
        Place place = new Place();
        try {
            placeById.setInt(1, anInt);
            ResultSet rs = placeById.executeQuery();
            while (rs.next()){
                place.setId(rs.getInt(1));
                place.setName(rs.getString(2));
                place.setAdress(rs.getString(3));
                place.setZipCode(rs.getString(4));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return place;
    }

    public int getMaxIdFromInstitutions() {
        ArrayList lista = new ArrayList<Integer>();
        Iterator it = this.getInstitutions().iterator();
        while (it.hasNext()){
            Institution vlasnik1 = (Institution) it.next();
            lista.add(vlasnik1.getId());
        }
        if(!lista.isEmpty()) {
            return (int) Collections.max(lista) + 1;
        }
        return 1;
    }

    public void addInstitiution(Institution institution) {
        try {
            addInstitution.setInt(1, institution.getId());
            addInstitution.setString(2, institution.getName());
            addInstitution.setInt(3, institution.getPlace().getId());
            addInstitution.setString(4, institution.getPhoneNumber());
            addInstitution.setString(5, institution.getEmail());
            addInstitution.setString(6, institution.getNameOfDirector());
            addInstitution.setString(7, institution.getSurenameOfDirector());
            addInstitution.setString(8, institution.getJmbgOfDirector());
            addInstitution.setDate(9, Date.valueOf(institution.getDateOfBirthOfDirector()));
            addInstitution.setString(10, institution.getPhoneNumberOfDirector());
            addInstitution.setString(11, institution.getEmailOfDirector());

            addInstitution.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public ObservableList getPlaces() {
        List<Place> list = new ArrayList<Place>();
        ObservableList<Place> observableList = FXCollections.observableList(list);
        ResultSet rs = null;
        try {
            rs = getPlaces.executeQuery();
            while(rs.next()){
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
        try {
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return observableList;
    }



    public int getMaxIdFromPlaces() {
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
            addPlace.setInt(1, place.getId());
            addPlace.setString(2, place.getName());
            addPlace.setString(3, place.getAdress());
            addPlace.setString(4, place.getZipCode());
            addPlace.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ObservableList getChildren(Institution institution, int a) {

        List<Child> list = new ArrayList<Child>();
        ObservableList<Child> observableList = FXCollections.observableList(list);
        ResultSet rs = null;
        try {
            rs = getChildren.executeQuery();
            while(rs.next()){
                Child child = new Child(rs.getString(2), rs.getString(3), rs.getInt(1));


                child.setDateOfBirth(rs.getDate(4).toLocalDate());
                child.setJmbg(rs.getString(5));
                child.setDwelling(this.placeById(rs.getInt(6)));
                child.setInstitution(this.institutionById(rs.getInt(7)));
                child.setChildWithSpecialNeeds(rs.getBoolean(8));
                child.setParentsName(rs.getString(9));
                child.setParentsSurename(rs.getString(10));
                child.setParentsJmbg(rs.getString(11));
                child.setParentsDateOfBirth(rs.getDate(12).toLocalDate());
                child.setPhoneNumber(rs.getString(13));

                if(a==1 && child.getInstitution().getId() == institution.getId() && Period.between(child.getDateOfBirth(), LocalDate.now()).getYears() <=2 && Period.between(child.getDateOfBirth(), LocalDate.now()).getYears() >=1) {
                    list.add(child);
                } else if(a==2 && child.getInstitution().getId() == institution.getId() && Period.between(child.getDateOfBirth(), LocalDate.now()).getYears() <6 && Period.between(child.getDateOfBirth(), LocalDate.now()).getYears() >=3){
                    list.add(child);
                } else if(a==3 && child.getInstitution().getId() == institution.getId() && child.isChildWithSpecialNeeds()){
                    list.add(child);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return observableList;
    }

    public ObservableList getEducators(Institution institution, int a){
        /*
        1 - obični
        2 - special needs
         */
        List<Educator> list = new ArrayList<Educator>();
        ObservableList<Educator> observableList = FXCollections.observableList(list);
        ResultSet rs = null;
        try {
            rs = getEducators.executeQuery();
            while(rs.next()){
                Educator educator = new Educator(rs.getString(2), rs.getString(3), rs.getInt(1));

                educator.setJmbg(rs.getString(4));
                educator.setDateOfBirth(rs.getDate(5).toLocalDate());
                educator.setDweling(this.placeById(rs.getInt(6)));
                educator.setInstitution(this.institutionById(rs.getInt(7)));
                educator.setEducatorForChildrenForSpecialNeeds(rs.getBoolean(8));
                educator.setEmail(rs.getString(9));
                educator.setPhoneNumber(rs.getString(10));

                if(educator.getInstitution().getId() == institution.getId() && a == 1 && !educator.isEducatorForChildrenForSpecialNeeds())
                list.add(educator);
                else if(educator.getInstitution().getId() == institution.getId() && a == 2 && educator.isEducatorForChildrenForSpecialNeeds())
                list.add(educator);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return observableList;
    }

    public ObservableList getAllEducators(){
        List<Educator> list = new ArrayList<Educator>();
        ObservableList<Educator> observableList = FXCollections.observableList(list);
        ResultSet rs = null;
        try {
            rs = getEducators.executeQuery();
            while(rs.next()){
                Educator educator = new Educator(rs.getString(2), rs.getString(3), rs.getInt(1));

                educator.setJmbg(rs.getString(4));
                educator.setDateOfBirth(rs.getDate(5).toLocalDate());
                educator.setDweling(this.placeById(rs.getInt(6)));
                educator.setInstitution(this.institutionById(rs.getInt(7)));
                educator.setEducatorForChildrenForSpecialNeeds(rs.getBoolean(8));
                educator.setEmail(rs.getString(9));
                educator.setPhoneNumber(rs.getString(10));

                list.add(educator);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return observableList;
    }


    public ObservableList getAllChildren() {
        List<Child> list = new ArrayList<Child>();
        ObservableList<Child> observableList = FXCollections.observableList(list);
        ResultSet rs = null;
        try {
            rs = getChildren.executeQuery();
            while(rs.next()){
                Child child = new Child(rs.getString(2), rs.getString(3), rs.getInt(1));

                child.setDateOfBirth(rs.getDate(4).toLocalDate());
                child.setJmbg(rs.getString(5));
                child.setDwelling(this.placeById(rs.getInt(6)));
                child.setInstitution(this.institutionById(rs.getInt(7)));
                child.setChildWithSpecialNeeds(rs.getBoolean(8));
                child.setParentsName(rs.getString(9));
                child.setParentsSurename(rs.getString(10));
                child.setParentsJmbg(rs.getString(11));
                child.setParentsDateOfBirth(rs.getDate(12).toLocalDate());
                child.setPhoneNumber(rs.getString(13));

                list.add(child);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return observableList;
    }

    private Institution institutionById(int anInt) {
        Institution institution = new Institution();
        try {
            institutionById.setInt(1, anInt);
            ResultSet rs = institutionById.executeQuery();
            while (rs.next()){
                institution.setId(rs.getInt(1));
                institution.setName(rs.getString(2));
                institution.setPlace(this.placeById(rs.getInt(3)));
                institution.setPhoneNumber(rs.getString(4));
                institution.setEmail(rs.getString(5));
                institution.setNameOfDirector(rs.getString(6));
                institution.setSurenameOfDirector(rs.getString(7));
                institution.setJmbgOfDirector(rs.getString(8));
                institution.setDateOfBirthOfDirector(rs.getDate(9).toLocalDate());
                institution.setPhoneNumberOfDirector(rs.getString(10));
                institution.setEmailOfDirector(rs.getString(11));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return institution;
    }

    public int getMaxIdFromChildren() {
        ArrayList lista = new ArrayList<Child>();
        Iterator it = this.getAllChildren().iterator();
        while (it.hasNext()){
            Child child = (Child) it.next();
            lista.add(child.getId());
        }
        if(!lista.isEmpty()) {
            return (int) Collections.max(lista) + 1;
        }
        return 1;
    }

    public void addChild(Child child) {
        try {
            addChild.setInt(1, child.getId());
            addChild.setString(2, child.getName());
            addChild.setString(3, child.getSurename());
            addChild.setDate(4, Date.valueOf(child.getDateOfBirth()));
            addChild.setString(5, child.getJmbg());
            addChild.setInt(6, child.getDwelling().getId());
            addChild.setInt(7, child.getInstitution().getId());
            addChild.setBoolean(8, child.isChildWithSpecialNeeds());
            addChild.setString(9, child.getParentsName());
            addChild.setString(10, child.getParentsSurename());
            addChild.setString(11, child.getJmbg());
            addChild.setDate(12, Date.valueOf(child.getParentsDateOfBirth()));
            addChild.setString(13, child.getPhoneNumber());

            addChild.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public int getMaxIdFromEducators() {
        ArrayList lista = new ArrayList<Educator>();
        Iterator it = this.getAllEducators().iterator();
        while (it.hasNext()){
            Educator educator = (Educator) it.next();
            lista.add(educator.getId());
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
            addEducator.setInt(6, educator.getDweling().getId());
            addEducator.setInt(7, educator.getInstitution().getId());
            addEducator.setBoolean(8, educator.isEducatorForChildrenForSpecialNeeds());
            addEducator.setString(9, educator.getEmail());
            addEducator.setString(10, educator.getPhoneNumber());
            addEducator.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeChild(Child child) {
        File file = new File(child.getName()+child.getSurename()+child.getId()+".xml");
        file.delete();
        try {
            removeChild.setInt(1, child.getId());
            removeChild.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void removeEducator(Educator educator) {

        try {
            removeEducator.setInt(1, educator.getId());
            removeEducator.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeInstitution(int institution) {

        try {
            removeInstitution.setInt(1, institution);
            removeInstitution.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void removePlace(int place) {

        try {
            removePlace.setInt(1, place);
            removePlace.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeChild1(int id, String name, String surename) {
        File file = new File(name + surename + id +".xml");
        file.delete();
        try {
            removeChild.setInt(1, id);
            removeChild.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeEducator1(int id) {

        try {
            removeEducator.setInt(1, id);
            removeEducator.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}