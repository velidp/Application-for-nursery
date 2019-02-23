package ba.unsa.etf.rpr.projekat;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DayTest {



    @Test
    void test() {
        Day day = new Day();
        day.setApsent(true);
        assertTrue(day.isApsent());
        day.setDate(LocalDate.now());
        assertEquals(day.getDate(), LocalDate.now());
        LocalDateTime time = LocalDateTime.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth(), 18, 0, 0);
        day.setArrivalTime(time);
        assertEquals(day.getArrivalTime(), time);
        day.setDepartureTime(time);
        assertEquals(day.getDepartureTime(), time);

    }

}