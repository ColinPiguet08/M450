import ch.tbz.m450.repository.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    private Address address;
    private Date date;

    @BeforeEach
    void setUp() {
        date = new Date();
        address = new Address(
                1,
                "Max",
                "Muster",
                "0791234567",
                date,
                "max@example.com",
                "Basel"
        );
    }

    @Test
    void testGetters() {
        assertEquals(1, address.getId());
        assertEquals("Max", address.getFirstname());
        assertEquals("Muster", address.getLastname());
        assertEquals("0791234567", address.getPhonenumber());
        assertEquals(date, address.getRegistrationDate());
        assertEquals("max@example.com", address.getEmail());
        assertEquals("Basel", address.getCity());
    }

    @Test
    void testSetters() {
        address.setFirstname("Anna");
        address.setCity("Zürich");

        assertEquals("Anna", address.getFirstname());
        assertEquals("Zürich", address.getCity());
    }
}
