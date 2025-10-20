package ch.tbz.m450.controller;

import ch.tbz.m450.repository.Address;
import ch.tbz.m450.service.AddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AddressController.class)
class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressService addressService;

    private Address address;

    @BeforeEach
    void setUp() {
        address = new Address();
        address.setId(1);
        address.setFirstname("Max");
        address.setLastname("Muster");
        address.setPhonenumber("0791234567");}

    @Test
    void testGetAllAddresses() throws Exception {
        when(addressService.getAll()).thenReturn(List.of(address));

        mockMvc.perform(get("/address"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstname").value("Max"))
                .andExpect(jsonPath("$[0].lastname").value("Muster"))
                .andExpect(jsonPath("$[0].phonenumber").value("0791234567"));
    }

    @Test
    void testGetAddressById_found() throws Exception {
        when(addressService.getAddress(1)).thenReturn(Optional.of(address));

        mockMvc.perform(get("/address/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname").value("Max"))
                .andExpect(jsonPath("$.lastname").value("Muster"))
                .andExpect(jsonPath("$.phonenumber").value("0791234567"));
    }

    @Test
    void testGetAddressById_notFound() throws Exception {
        when(addressService.getAddress(99)).thenReturn(Optional.empty());

        mockMvc.perform(get("/address/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateAddress() throws Exception {
        when(addressService.save(any(Address.class))).thenReturn(address);

        String addressJson = """
                {
                    "firstname": "Max",
                    "lastname": "Muster",
                    "phonenumber": "0791234567"
                }
                """;

        mockMvc.perform(post("/address")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(addressJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstname").value("Max"))
                .andExpect(jsonPath("$.lastname").value("Muster"))
                .andExpect(jsonPath("$.phonenumber").value("0791234567"));
    }
}
