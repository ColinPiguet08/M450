import ch.tbz.m450.repository.Address;
import ch.tbz.m450.util.AddressComparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class AddressComparatorTest {

    private AddressComparator comparator;
    private Address a1;
    private Address a2;

    @BeforeEach
    void setUp() {
        comparator = new AddressComparator();

        a1 = new Address();
        a1.setId(1);
        a1.setFirstname("Max");
        a1.setLastname("Muster");
        a1.setPhonenumber("0791234567");
        a1.setRegistrationDate(new Date(1000));
        a1.setEmail("max@example.com");
        a1.setCity("Basel");

        a2 = new Address();
        a2.setId(2);
        a2.setFirstname("Max");
        a2.setLastname("Muster");
        a2.setPhonenumber("0791234567");
        a2.setRegistrationDate(new Date(2000));
        a2.setEmail("max@example.com");
        a2.setCity("Zürich");
    }

    @Test
    void testCompareByCity() {
        int result = comparator.compare(a1, a2);
        assertTrue(result < 0, "Basel should come before Zürich alphabetically");
    }

    @Test
    void testCompareByEmail() {
        a1.setCity("Basel");
        a2.setCity("Basel");
        a2.setEmail("zzz@example.com");

        int result = comparator.compare(a1, a2);
        assertTrue(result < 0, "Emails should be compared alphabetically");
    }

    @Test
    void testCompareByLastnameFirstname() {
        a2.setLastname("Ammann");
        int result = comparator.compare(a1, a2);
        assertTrue(result > 0, "Muster should come after Ammann alphabetically");
    }

    @Test
    void testCompareByRegistrationDate() {
        a1.setLastname("Muster");
        a1.setFirstname("Max");
        a1.setCity("Basel");
        a1.setEmail("max@example.com");
        a1.setPhonenumber("0791234567");

        a2.setLastname("Muster");
        a2.setFirstname("Max");
        a2.setCity("Basel");
        a2.setEmail("max@example.com");
        a2.setPhonenumber("0791234567");

        a1.setRegistrationDate(new Date(1000));
        a2.setRegistrationDate(new Date(2000));

        int result = comparator.compare(a1, a2);
        assertTrue(result < 0, "Older registration date should come first");
    }

    @Test
    void testCompareEqualAddresses() {
        a2.setCity("Basel");
        a2.setEmail("max@example.com");
        a2.setRegistrationDate(a1.getRegistrationDate());
        int result = comparator.compare(a1, a2);
        assertEquals(0, result, "Identical addresses should compare equal");
    }

    @Test
    void testCompareWithNullFields() {
        a1.setCity(null);
        a2.setCity("Basel");
        int result = comparator.compare(a1, a2);
        assertTrue(result < 0, "Null city should be treated as smaller value");
    }
}
