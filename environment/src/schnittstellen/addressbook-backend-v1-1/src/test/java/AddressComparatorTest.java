import ch.tbz.m450.repository.Address;
import ch.tbz.m450.util.AddressComparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

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
    void testSortByCity() {
        List<Address> addresses = new ArrayList<>(List.of(a2, a1));
        addresses.sort(comparator);

        assertEquals(a1, addresses.get(0), "Basel should come before Zürich");
    }

    @Test
    void testSortByEmail() {
        a1.setCity("Basel");
        a2.setCity("Basel");
        a1.setEmail("anna@example.com");
        a2.setEmail("zzz@example.com");

        List<Address> addresses = new ArrayList<>(List.of(a2, a1));
        addresses.sort(comparator);

        assertEquals(a1, addresses.get(0), "anna@example.com should come before zzz@example.com");
    }

    @Test
    void testSortByLastnameFirstname() {
        a1.setLastname("Muster");
        a2.setLastname("Ammann");
        a1.setFirstname("Max");
        a2.setFirstname("Anna");

        List<Address> addresses = new ArrayList<>(List.of(a1, a2));
        addresses.sort(comparator);

        assertEquals(a2, addresses.get(0), "Ammann should come before Muster alphabetically");
    }

    @Test
    void testSortByRegistrationDate() {
        a1.setCity("Basel");
        a2.setCity("Basel");
        a1.setEmail("max@example.com");
        a2.setEmail("max@example.com");
        a1.setRegistrationDate(new Date(1000));
        a2.setRegistrationDate(new Date(2000));

        List<Address> addresses = new ArrayList<>(List.of(a2, a1));
        addresses.sort(comparator);

        assertEquals(a1, addresses.get(0), "Older registration date should come first");
    }

    @Test
    void testSortEqualAddresses() {
        a2.setCity("Basel");
        a2.setEmail("max@example.com");
        a2.setRegistrationDate(a1.getRegistrationDate());

        List<Address> addresses = new ArrayList<>(List.of(a2, a1));
        addresses.sort(comparator);

        assertEquals(a2, addresses.get(0));
        assertEquals(a1, addresses.get(1));
        assertEquals(0, comparator.compare(a1, a2), "Equal addresses should compare equal");
    }

    @Test
    void testSortWithNullValues() {
        a1.setCity(null);
        a2.setCity("Basel");

        List<Address> addresses = new ArrayList<>(List.of(a2, a1));
        addresses.sort(comparator);

        assertEquals(a1, addresses.get(0), "Null city should be treated as smaller value");
    }
}
