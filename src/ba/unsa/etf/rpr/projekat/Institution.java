package ba.unsa.etf.rpr.projekat;

import java.time.LocalDate;

public class Institution {
    int id;
    String name, phoneNumber, email, nameOfDirector, surenameOfDirector, jmbgOfDirector, phoneNumberOfDirector, emailOfDirector;
    LocalDate dateOfBirthOfDirector;
    Place place;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNameOfDirector() {
        return nameOfDirector;
    }

    public void setNameOfDirector(String nameOfDirector) {
        this.nameOfDirector = nameOfDirector;
    }

    public String getSurenameOfDirector() {
        return surenameOfDirector;
    }

    public void setSurenameOfDirector(String surenameOfDirector) {
        this.surenameOfDirector = surenameOfDirector;
    }

    public String getJmbgOfDirector() {
        return jmbgOfDirector;
    }

    public void setJmbgOfDirector(String jmbgOfDirector) {
        this.jmbgOfDirector = jmbgOfDirector;
    }

    public String getPhoneNumberOfDirector() {
        return phoneNumberOfDirector;
    }

    public void setPhoneNumberOfDirector(String phoneNumberOfDirector) {
        this.phoneNumberOfDirector = phoneNumberOfDirector;
    }

    public String getEmailOfDirector() {
        return emailOfDirector;
    }

    public void setEmailOfDirector(String emailOfDirector) {
        this.emailOfDirector = emailOfDirector;
    }

    public LocalDate getDateOfBirthOfDirector() {
        return dateOfBirthOfDirector;
    }

    public void setDateOfBirthOfDirector(LocalDate dateOfBirthOfDirector) {
        this.dateOfBirthOfDirector = dateOfBirthOfDirector;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public String toString(){
        return name;
    }
}