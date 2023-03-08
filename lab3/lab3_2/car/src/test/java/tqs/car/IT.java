package tqs.car;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import tqs.car.data.Car;
import tqs.car.data.CarRepository;

import java.io.IOException;
import java.util.List;

import org.springframework.test.context.TestPropertySource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = Lab3CarServiceApplication.class)
@AutoConfigureMockMvc


@TestPropertySource(locations = "application-integrationtest.properties")
public class IT {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CarRepository carRepository;


    private Car a;
    @BeforeEach
    public void setUp() {
        a = new Car("audi","a4"); 
    }

    @AfterEach
    public void resetDb() {
        carRepository.deleteAll();
    }

    @Test
    public void whenValidCar_thenCreateCar() throws IOException, Exception {
        mvc.perform(post("/api/cars").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(a)));

        List<Car> car_found = carRepository.findAll();
        assertThat(car_found).extracting(Car::getMaker).containsOnly("audi");
    }

    @Test
    public void givenCars_whenGetAllCars_thenStatus200() throws IOException, Exception {
        Car b = new Car("bmw","i8");
        Car c = new Car("chervolet","camaro");

        carRepository.save(a);
        carRepository.save(b);
        carRepository.save(c);

        mvc.perform(get("/api/cars").contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(3))))
            .andExpect(jsonPath("$[0].maker", is("audi")))
            .andExpect(jsonPath("$[1].maker", is("bmw")));

    }

    @Test
    public void whenValidIdCar_thenStatus200() throws IOException, Exception {
        carRepository.save(a);

        String url="/api/cars/"+a.getCarId();
        mvc.perform(get(url).contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.maker", is(a.getMaker())));
    }




}
