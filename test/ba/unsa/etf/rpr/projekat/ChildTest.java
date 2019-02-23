package ba.unsa.etf.rpr.projekat;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ChildTest {

    @Test
    void test() {
        /*kreiramo djete*/
        Child child = new Child("djete", "djetovic", 7);
        String pathname = child.getName()+child.getSurename() + child.getId() +".xml";

        /*kreiramo listu dana*/
        ArrayList days = new ArrayList<Day>();

        /*kreiramo dan*/
        Day day1 = new Day();
        day1.setArrivalTime(LocalDateTime.now());
        day1.setDate(LocalDate.now());
        ArrayList<String> comments = new ArrayList<String>();
        comments.add("Nice child.");
        comments.add("Good");
        day1.setComments(comments);
        day1.setDepartureTime(LocalDateTime.now().plusHours(5));
        day1.setApsent(true);

        /*dodajemo dan u lisut dana*/
        days.add(day1);

        /*vrsimo serijalizaciju*/
        child.serialize(days);

        /*vrsimo deserijalizaciju kako bi provjerili da li je kreirani dan serijalizovan kako treba*/
        Day dayDeserialized = child.desrialize().get(0);

        assertEquals(dayDeserialized.getArrivalTime(), day1.getArrivalTime());
        assertEquals(dayDeserialized.getDepartureTime(), day1.getDepartureTime());
        assertEquals(dayDeserialized.getComments().get(0), "Nice child.");
        assertEquals(dayDeserialized.getComments().get(1), "Good");
        assertEquals(dayDeserialized.getDate(), day1.getDate());
        assertEquals(child.getId(), 7);
        child.setId(99);
        assertEquals(child.getId(), 99);
        assertEquals(child.getName(), "djete");
        child.setName("djete1");
        assertEquals(child.getName(), "djete1");
        assertEquals(child.getSurename(), "djetovic");
        child.setSurename("djetetovic1");
        assertEquals(child.getSurename(), "djetetovic1");
        child.setJmbg("111");
        assertEquals(child.getJmbg(), "111");
        child.setJmbg("111");
        assertEquals(child.getJmbg(), "111");
        child.setParentsName("parent");
        assertEquals(child.getParentsName(), "parent");
        child.setParentsName("parent");
        assertEquals(child.getParentsName(), "parent");
        child.setParentsSurename("surename");
        assertEquals(child.getParentsSurename(), "surename");
        child.setParentsSurename("surename");
        assertEquals(child.getParentsSurename(), "surename");
        child.setParentsJmbg("jmbg");
        assertEquals(child.getParentsJmbg(), "jmbg");
        child.setParentsJmbg("jmbg");
        assertEquals(child.getParentsJmbg(), "jmbg");
        child.setPhoneNumber("000");
        assertEquals(child.getPhoneNumber(), "000");
        child.setPhoneNumber("000");
        assertEquals(child.getPhoneNumber(), "000");
        child.setDateOfBirth(LocalDate.now());
        assertEquals(child.getDateOfBirth(), LocalDate.now());
        child.setDateOfBirth(LocalDate.now());
        assertEquals(child.getDateOfBirth(), LocalDate.now());
        child.setParentsDateOfBirth(LocalDate.now());
        assertEquals(child.getParentsDateOfBirth(), LocalDate.now());
        child.setParentsDateOfBirth(LocalDate.now());
        assertEquals(child.getParentsDateOfBirth(), LocalDate.now());
        child.setChildWithSpecialNeeds(true);
        assertTrue(child.isChildWithSpecialNeeds());
        child.setChildWithSpecialNeeds(true);
        assertTrue(child.isChildWithSpecialNeeds());
        Institution inst1 = new Institution();
        inst1.setName("First institution");
        child.setInstitution(inst1);
        assertEquals(child.getInstitution(), inst1);
        Place place = new Place();
        place.setName("First institution");
        child.setDwelling(place);
        assertEquals(child.getDwelling(), place);


        /*brisemo kreiranu datoteku jer vise nije potrebna*/
        File file = new File(pathname);
        file.delete();
    }







}