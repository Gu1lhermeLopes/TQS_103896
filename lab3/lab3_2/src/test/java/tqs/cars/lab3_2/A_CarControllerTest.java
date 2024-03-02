package tqs.cars.lab3_2;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import tqs.cars.lab3_2.boundary.CarController;
import tqs.cars.lab3_2.model.Car;
import tqs.cars.lab3_2.service.CarService;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;


@WebMvcTest(CarController.class)
public class A_CarControllerTest {

    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private CarService carService;

    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    void whenPostCar_thenCreateCar( ) throws Exception {
        Car car = new Car("tesla", "model s");
        when( carService.save(Mockito.any()) ).thenReturn( car);

        mvc.perform(
                post("/api/cars").contentType("application/json").content(JsonUtils.toJson(car)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.maker", is("tesla")))
                .andExpect(jsonPath("$.carId", is(car.getCarId())));

        verify(carService, times(1)).save(Mockito.any());
   }
    
   @Test
   void whenGetCars_thenReturnCars( ) throws Exception {
       Car car = new Car("tesla", "model s");
       Car car2 = new Car("tesla", "model 3");
       when( carService.getAllCars() ).thenReturn( Arrays.asList(car, car2) );

       mvc.perform(get("/api/cars").contentType("application/json"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$", hasSize(2)))
               .andExpect(jsonPath("$[0].maker", is(car.getMaker())))
               .andExpect(jsonPath("$[1].maker", is(car2.getMaker())));

       verify(carService, times(1)).getAllCars();
   }



    
}