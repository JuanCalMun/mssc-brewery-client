package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
import guru.springframework.msscbreweryclient.web.model.CustomerDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
public class BreweryClient {

    public final static String BEER_PATH_V1 = "/api/v1/beer";
    public final static String CUSTOMER_PATH_V1 = "/api/v1/customer";
    private final String apihost;

    private RestTemplate restTemplate;

    public BreweryClient(@Value("${sfg.brewery.apihost}") String apihost,
                         RestTemplateBuilder restTemplateBuilder) {
        this.apihost = apihost;
        restTemplate = restTemplateBuilder.build();
    }

    public BeerDto getBeerByUUID(UUID beerId) {
        return restTemplate.getForObject(
                apihost + BEER_PATH_V1 + "/" + beerId.toString(),
                BeerDto.class);
    }

    public ResponseEntity createBeer(BeerDto beerToCreate) {
        return restTemplate.postForEntity(apihost + BEER_PATH_V1, beerToCreate, ResponseEntity.class);
    }

    public void modifyBeer(UUID beerId, BeerDto beerNewValue) {
        restTemplate.put(
                apihost + BEER_PATH_V1 + "/" + beerId.toString(),
                beerNewValue);

    }

    public void deleteBeer(UUID beerId) {
        restTemplate.delete(apihost + BEER_PATH_V1 + "/" + beerId.toString());
    }

    public CustomerDto getCustomerByUUID(UUID customerId) {
        return restTemplate.getForObject(
                apihost + CUSTOMER_PATH_V1 + "/" + customerId.toString(),
                CustomerDto.class);
    }

    public ResponseEntity createCustomer(CustomerDto customerToCreate) {
        return restTemplate.postForEntity(apihost + CUSTOMER_PATH_V1, customerToCreate, ResponseEntity.class);
    }

    public void modifyCustomer(UUID customerId, CustomerDto customerNewValue) {
        restTemplate.put(
                apihost + CUSTOMER_PATH_V1 + "/" + customerId.toString(),
                customerNewValue);

    }

    public void deleteCustomer(UUID customerId) {
        restTemplate.delete(apihost + CUSTOMER_PATH_V1 + "/" + customerId.toString());
    }

}
