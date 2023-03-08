package tqs.car;

import static org.mockito.Mockito.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import tqs.car.boundary.CarController;
import tqs.car.data.Car;
import tqs.car.service.CarManagerService;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(CarController.class)
public class A {

	@Autowired
    private MockMvc mvc;

	@MockBean
    private CarManagerService service;

	@BeforeEach
    public void setUp() throws Exception {
    }

	@Test
    void whenPostCar_thenCreateCar( ) throws Exception {
        Car car = new Car("audi", "a4");

        when( service.save(Mockito.any()) ).thenReturn( car);

        mvc.perform(
                post("/api/cars").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(car)))
                .andExpect(status().isCreated())
				.andExpect(jsonPath("$.maker", is("audi")))
                .andExpect(jsonPath("$.model", is("a4")));

        verify(service, times(1)).save(Mockito.any());

    }


	@Test
    void givenManyCars_whenGetCars_thenReturnJsonArray() throws Exception {
        Car a = new Car("audi", "a4");
        Car b = new Car("bmw", "i8");
        Car c = new Car("chervolet", "camaro");

        List<Car> allCars = Arrays.asList(a, b, c);

        when( service.getAllCars()).thenReturn(allCars);

        mvc.perform(
                get("/api/cars").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].maker", is(a.getMaker())))
                .andExpect(jsonPath("$[1].maker", is(b.getMaker())))
                .andExpect(jsonPath("$[2].maker", is(c.getMaker())));

        verify(service, times(1)).getAllCars();
    }
}
