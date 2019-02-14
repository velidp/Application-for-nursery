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
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Child {
    private int id;
    private String name, surename, jmbg, parentsName, parentsSurename, parentsJmbg, phoneNumber;
    private LocalDate dateOfBirth, parentsDateOfBirth;
    private boolean childWithSpecialNeeds;
    private Institution institution;
    private Place dwelling;

    private ArrayList<Day> days = new ArrayList<Day>();

    public void serialize(ArrayList<Day> lista){

        try {
            FileOutputStream fileOut = new FileOutputStream(this.getName() + this.getSurename() + this.id + ".xml");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(lista);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }


    public ArrayList<Day> desrialize(){
        ArrayList<Day> komentari = null;
        try {
            FileInputStream fileIn = new FileInputStream(this.getName() + this.getSurename() + this.id + ".xml");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            komentari = (ArrayList<Day>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
        }
        return komentari;
    }


    public Child(String name, String surename, int id){
        this.id = id;
        this.name = name;
        this.surename = surename;

        Day day = new Day();

        day.setDepartureTime(LocalDateTime.MIN);
        day.setComments(new ArrayList<String>());
        day.setDate(LocalDate.MIN);
        day.setApsent(false);
        day.setArrivalTime(LocalDateTime.MIN);

        days.add(day);



        File f = new File(this.getName() + this.getSurename() + this.id + ".xml");
        if(!(f.exists() && !f.isDirectory())) {
            try {

                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder;
                Document doc;
                docBuilder = docFactory.newDocumentBuilder();
                doc = docBuilder.newDocument();
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = null;
                transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File(this.getName() + this.getSurename() + this.id + ".xml"));
                transformer.transform(source, result);
                try {
                    FileOutputStream fileOut = new FileOutputStream(this.getName() + this.getSurename() + this.id + ".xml");
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    out.writeObject(days);
                    out.close();
                    fileOut.close();

                } catch (IOException i) {
                    i.printStackTrace();
                }
            } catch (
                    ParserConfigurationException | TransformerException pce) {
                pce.printStackTrace();
            }
        }

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

    public String getParentsName() {
        return parentsName;
    }

    public void setParentsName(String parentsName) {
        this.parentsName = parentsName;
    }

    public String getParentsSurename() {
        return parentsSurename;
    }

    public void setParentsSurename(String parentsSurename) {
        this.parentsSurename = parentsSurename;
    }

    public String getParentsJmbg() {
        return parentsJmbg;
    }

    public void setParentsJmbg(String parentsJmbg) {
        this.parentsJmbg = parentsJmbg;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getParentsDateOfBirth() {
        return parentsDateOfBirth;
    }

    public void setParentsDateOfBirth(LocalDate parentsDateOfBirth) {
        this.parentsDateOfBirth = parentsDateOfBirth;
    }

    public boolean isChildWithSpecialNeeds() {
        return childWithSpecialNeeds;
    }

    public void setChildWithSpecialNeeds(boolean childWithSpecialNeeds) {
        this.childWithSpecialNeeds = childWithSpecialNeeds;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public Place getDwelling() {
        return dwelling;
    }

    public void setDwelling(Place dwelling) {
        this.dwelling = dwelling;
    }

    public ArrayList<Day> getDays() {
        return days;
    }

    public void setDays(ArrayList<Day> days) {
        this.days = days;
    }

    public String toString(){
        return id + " " + name + surename;
    }
}