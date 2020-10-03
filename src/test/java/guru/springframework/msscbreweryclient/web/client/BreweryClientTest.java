package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
import guru.springframework.msscbreweryclient.web.model.CustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class BreweryClientTest {

    @Autowired
    private BreweryClient breweryClient;

    @Test
    void getBeerByUUID() {
        BeerDto beerDto = breweryClient.getBeerByUUID(UUID.randomUUID());
        assertNotNull(beerDto);
    }

    @Test
    void createBeer() {
        BeerDto beertoCreate = BeerDto.builder().beerName("New beer").build();
        ResponseEntity response = breweryClient.createBeer(beertoCreate);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void modifyBeer() {
        BeerDto beerNewValue = BeerDto.builder().beerName("Updated beer").build();
        UUID beerIdToUpdate = UUID.randomUUID();
        breweryClient.modifyBeer(beerIdToUpdate, beerNewValue);
    }

    @Test
    void deleteBeer() {
        UUID beerIdToDelete = UUID.randomUUID();
        breweryClient.deleteBeer(beerIdToDelete);
    }

    @Test
    void getCustomerByUUID() {
        CustomerDto customerDto = breweryClient.getCustomerByUUID(UUID.randomUUID());
        assertNotNull(customerDto);
    }

    @Test
    void createCustomer() {
        CustomerDto customerToCreate = CustomerDto.builder().name("New customer").build();
        ResponseEntity response = breweryClient.createCustomer(customerToCreate);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void modifyCustomer() {
        CustomerDto customerNewValue = CustomerDto.builder().name("Updated customer").build();
        UUID customerIdToUpdate = UUID.randomUUID();
        breweryClient.modifyCustomer(customerIdToUpdate, customerNewValue);
    }

    @Test
    void deleteCustomer() {
        UUID customerIdToDelete = UUID.randomUUID();
        breweryClient.deleteCustomer(customerIdToDelete);
    }
}
