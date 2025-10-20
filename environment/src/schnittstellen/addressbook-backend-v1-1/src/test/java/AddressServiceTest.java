import ch.tbz.m450.repository.Address;
import ch.tbz.m450.repository.AddressRepository;
import ch.tbz.m450.service.AddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressService addressService;

    private Address address1;
    private Address address2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        address1 = new Address(
                1,
                "Max",
                "Muster",
                "0791234567",
                new Date(),
                "max@example.com",
                "Basel"
        );

        address2 = new Address(
                2,
                "Anna",
                "Meier",
                "0787654321",
                new Date(),
                "anna@example.com",
                "Zürich"
        );
    }

    @Test
    void testSaveAddress() {
        when(addressRepository.save(address1)).thenReturn(address1);

        Address saved = addressService.save(address1);
        assertNotNull(saved);
        assertEquals("Max", saved.getFirstname());
        verify(addressRepository, times(1)).save(address1);
    }

    @Test
    void testGetAllAddresses() {
        when(addressRepository.findAll()).thenReturn(java.util.List.of(address1, address2));

        var result = addressService.getAll();
        assertEquals(2, result.size());
        verify(addressRepository, times(1)).findAll();
    }
}
