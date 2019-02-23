package ba.unsa.etf.rpr.projekat;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class InstitutionTest {

    @Test
    void test() {
        Institution inst = new Institution();
        inst.setId(87);
        assertEquals(inst.getId(), 87);
        inst.setName("First");
        assertEquals(inst.getName(), "First");
        inst.setPhoneNumber("065897654");
        assertEquals(inst.getPhoneNumber(), "065897654");
        inst.setEmail("first@gmail.com");
        assertEquals(inst.getEmail(), "first@gmail.com");
        inst.setNameOfDirector("dir");
        assertEquals(inst.getNameOfDirector(), "dir");
        inst.setSurenameOfDirector("sur");
        assertEquals(inst.getSurenameOfDirector(), "sur");
        inst.setJmbgOfDirector("jmbg");
        assertEquals(inst.getJmbgOfDirector(), "jmbg");
        inst.setPhoneNumberOfDirector("061222333");
        assertEquals(inst.getPhoneNumberOfDirector(), "061222333");
        inst.setEmailOfDirector("dir@gmail.com");
        assertEquals(inst.getEmailOfDirector(), "dir@gmail.com");
        inst.setDateOfBirthOfDirector(LocalDate.now());
        assertEquals(inst.getDateOfBirthOfDirector(), LocalDate.now());
        Place place = new Place();
        inst.setPlace(place);
        assertEquals(inst.getPlace(), place);
    }


}