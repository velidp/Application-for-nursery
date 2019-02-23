package ba.unsa.etf.rpr.projekat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlaceTest {

    @Test
    void test() {
        Place place = new Place();
        place.setId(89);
        assertEquals(place.getId(), 89);
        place.setId(89);
        assertEquals(place.getId(), 89);
        place.setName("place");
        assertEquals(place.getName(), "place");
        place.setAdress("adr");
        assertEquals(place.getAdress(), "adr");
        place.setZipCode("71000");
        assertEquals(place.getZipCode(), "71000");
    }


}