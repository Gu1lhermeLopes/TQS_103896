package tqs.cars.lab3_2;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment; // Import the missing class
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

import tqs.cars.lab3_2.data.CarRepository;
import tqs.cars.lab3_2.model.Car;
import java.util.List; // Add the missing import


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)


@TestPropertySource(locations = "application-integrationtest.properties")
public class E_CarRestControllerTemplateIT {
    

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarRepository carRepository;

    @AfterEach
    public void resetDb() {
        carRepository.deleteAll();
    }


    @Test
    void whenValidInput_thenCreateCar() {
        Car car = new Car("maker", "model");
        ResponseEntity<Car> entity = restTemplate.postForEntity("/api/cars", car, Car.class);
        List<Car> found = carRepository.findAll();
        assertThat(found).size().isEqualTo(1);
        assertThat(found).extracting(Car::getModel).containsOnly(car.getModel());
    }


    @Test
    void givenCars_whenGetCars_thenStatus200() {
        createTestCar("maker1", "model1");
        createTestCar("maker2", "model2");
        ResponseEntity<List<Car>> response = restTemplate.exchange("/api/cars", HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {
        });
        List<Car> cars = response.getBody();
        assertAll(
                () -> assertThat(cars).size().isEqualTo(2),
                () -> assertThat(cars).extracting(Car::getModel).contains("model1", "model2")
        );
    }



    private void createTestCar(String maker, String model) {
        Car car = new Car(maker, model);
        carRepository.saveAndFlush(car);
    }


}
