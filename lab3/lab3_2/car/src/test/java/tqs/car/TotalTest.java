package tqs.car;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import tqs.car.data.*;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;



@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class TotalTest {

    @LocalServerPort
    int randomServerPort;

    // a REST client that is test-friendly
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarRepository repository;

    @AfterEach
    public void resetDb() {
        repository.deleteAll();
    }


    @Test
    void whenValidInput_thenCreateCar() {
       Car a = new Car("audi", "a4");
       ResponseEntity<Car> entity = restTemplate.postForEntity("/api/cars", a, Car.class);

       List<Car> found = repository.findAll();
       assertThat(found).extracting(Car::getMaker).containsOnly("audi");
   }

   @Test
   void givenEmployees_whenGetEmployees_thenStatus200()  {
    createTestCar("audi", "a4");
    createTestCar("bmw", "i8");


      ResponseEntity<List<Car>> response = restTemplate
              .exchange("/api/cars", HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {
              });

      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
      assertThat(response.getBody()).extracting(Car::getMaker).containsExactly("audi", "bmw");

  }

  private void createTestCar(String maker, String model) {
    Car car = new Car(maker, model);
    repository.saveAndFlush(car);
}

}
