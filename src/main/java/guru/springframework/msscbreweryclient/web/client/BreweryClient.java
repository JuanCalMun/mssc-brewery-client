package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
@ConfigurationProperties(value = "sfg.brewery", ignoreUnknownFields = false)
public class BreweryClient {

    public final static String BEER_PATH_V1 = "/api/v1/beer";
    private String apihost;

    private RestTemplate restTemplate;

    public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
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

    public void setApihost(String apihost) {
        this.apihost = apihost;
    }
}
