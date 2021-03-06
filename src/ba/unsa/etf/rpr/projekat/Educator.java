package ba.unsa.etf.rpr.projekat;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.time.LocalDate;

public class Educator {
    private int id;
    private String name, surename, jmbg, email, phoneNumber;
    private LocalDate dateOfBirth;
    private Place dweling;
    private Institution institution;
    private boolean educatorForChildrenForSpecialNeeds;


    public Educator(String name, String surename, int id){
        this.id = id;
        this.name = name;
        this.surename = surename;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

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

    public String getSurename() {
        return surename;
    }

    public void setSurename(String surename) {
        this.surename = surename;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Place getDweling() {
        return dweling;
    }

    public void setDweling(Place dweling) {
        this.dweling = dweling;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public boolean isEducatorForChildrenForSpecialNeeds() {
        return educatorForChildrenForSpecialNeeds;
    }

    public void setEducatorForChildrenForSpecialNeeds(boolean educatorForChildrenForSpecialNeeds) {
        this.educatorForChildrenForSpecialNeeds = educatorForChildrenForSpecialNeeds;
    }
}
