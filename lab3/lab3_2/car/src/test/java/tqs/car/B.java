package tqs.car;

import static org.mockito.Mockito.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.internal.verification.VerificationModeFactory;



import tqs.car.data.*;
import tqs.car.service.CarManagerService;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;



@ExtendWith(MockitoExtension.class)
public class B {

    @Mock( lenient = true)
    private CarRepository carRepository;

    @InjectMocks
    private CarManagerService service;

    private Car a,b,c;

    @BeforeEach
    public void setUp() {
        a = new Car("audi", "a4");
        b = new Car("bmw", "i8");
        c = new Car("chervolet", "camaro");
        List<Car> allcars = Arrays.asList(a, b, c);
        
        Mockito.when(carRepository.findAll()).thenReturn(allcars);
        Mockito.when(carRepository.findByCarId(-1L)).thenReturn(null);

        Mockito.when(carRepository.findByCarId(a.getCarId())).thenReturn(a);




    }

    @Test
    public void whenValidId_thenCarShouldBeFound() {
        Car a = new Car("audi", "a4");
        Optional<Car> found = service.getCarDetails(a.getCarId());
        assertThat(found.get().getMaker()).isEqualTo("audi");
        verify(carRepository, VerificationModeFactory.times(1)).findByCarId(a.getCarId());

    }

    @Test
    void whenSearchInvalidId_thenCarShouldNotBeFound() {
        Optional<Car>  dumb = service.getCarDetails(-1L);
        assertThat(dumb.isEmpty());
        verifyFindByIdIsCalledOnce(-1L);
   }

    @Test
    public void given3Cars_whengetAll_thenReturn3Records() {
        Car a = new Car("audi", "a4");
        Car b = new Car("bmw", "i8");
        Car c = new Car("chervolet", "camaro");
        
       List<Car> list = service.getAllCars();
       verifyFindAllCarsIsCalledOnce();
       assertThat(list).hasSize(3).extracting(Car::getMaker).contains(a.getMaker(), b.getMaker(), c.getMaker());
   }

   @Test
   public void whensave_thenCarShouldBeSaved() {
       
       
       when(carRepository.save(a)).thenReturn(a);

       Car car_saved = service.save(a);
       
       verify(carRepository, VerificationModeFactory.times(1)).save(a);
       assertThat(car_saved.getMaker()).isEqualTo("audi");
   }

    private void verifyFindByIdIsCalledOnce(Long id) {
        Mockito.verify(carRepository, VerificationModeFactory.times(1)).findByCarId(id);
    }
    private void verifyFindAllCarsIsCalledOnce() {
        Mockito.verify(carRepository, VerificationModeFactory.times(1)).findAll();
    }




}
