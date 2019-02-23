package ba.unsa.etf.rpr.projekat;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EducatorTest {

    @Test
    void test() {
        Educator educator = new Educator("edu", "edi", 5);
        educator.setEmail("educator@gmail.com");
        String email = educator.getEmail();
        assertEquals(email, "educator@gmail.com");
        educator.setEmail("educator@gmail.com");
        assertEquals(educator.getEmail(), "educator@gmail.com");
        educator.setPhoneNumber("063159753");
        assertEquals(educator.getPhoneNumber(), "063159753");
        educator.setPhoneNumber("063159753");
        assertEquals(educator.getPhoneNumber(), "063159753");
        assertEquals(educator.getId(), 5);
        educator.setId(6);
        assertEquals(educator.getId(), 6);
        assertEquals(educator.getName(), "edu");
        educator.setName("edu1");
        assertEquals(educator.getName(), "edu1");
        assertEquals(educator.getSurename(), "edi");
        educator.setSurename("edi1");
        assertEquals(educator.getSurename(), "edi1");
        educator.setJmbg("jmbg");
        assertEquals(educator.getJmbg(), "jmbg");
        educator.setJmbg("jmbg");
        assertEquals(educator.getJmbg(), "jmbg");
        educator.setDateOfBirth(LocalDate.now());
        assertEquals(educator.getDateOfBirth(), LocalDate.now());
        educator.setDateOfBirth(LocalDate.now());
        assertEquals(educator.getDateOfBirth(), LocalDate.now());
        Place place = new Place();
        educator.setDweling(place);
        assertEquals(educator.getDweling(), place);
        Institution inst = new Institution();
        educator.setInstitution(inst);
        assertEquals(educator.getInstitution(), inst);
        educator.setEducatorForChildrenForSpecialNeeds(true);
        assertTrue(educator.isEducatorForChildrenForSpecialNeeds());
    }

}