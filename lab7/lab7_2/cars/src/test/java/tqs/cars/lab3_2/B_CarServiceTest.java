package tqs.cars.lab3_2;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;

import tqs.cars.lab3_2.data.CarRepository;
import tqs.cars.lab3_2.model.Car;
import tqs.cars.lab3_2.service.CarServiceImpl;

@ExtendWith(MockitoExtension.class)
class B_CarServiceTest {

    @Mock(lenient = true)
    private CarRepository carRepository;

    @InjectMocks
    private CarServiceImpl carService;

    @BeforeEach
    public void setUp() {
        //these expectations provide an alternative to the use of the repository
        Car car1 = new Car("tesla", "model s");
        car1.setCarId(1L);
        Car car2 = new Car("audi", "a4");
        Car car3 = new Car("toyota", "ayris");
        Mockito.when(carRepository.findByCarId(car1.getCarId())).thenReturn(car1);
        Mockito.when(carRepository.findByCarId(car2.getCarId())).thenReturn(car2);
        Mockito.when(carRepository.findByCarId(car3.getCarId())).thenReturn(car3);
        Mockito.when(carRepository.findByCarId(-1L)).thenReturn(null);
        Mockito.when(carRepository.findAll()).thenReturn(Arrays.asList(car1, car2, car3));
    }

    @Test
    void whenValidId_thenCarShouldBeFound() {
        Long id = 1L;
        Optional<Car> found = carService.getCarDetails(id);
        assertThat(found.get().getCarId()).isEqualTo(id);
        verifyFindByIdIsCalledOnce(id);
    }

    @Test
    void whenInValidId_thenCarShouldNotBeFound() {
        Long id = -1L;
        Optional<Car> found = carService.getCarDetails(id);
        assertThat(found).isEmpty();
        verifyFindByIdIsCalledOnce(id);
    }
    
    @Test
    void given3Cars_whengetAll_thenReturn3Records() {
        Car car1 = new Car("tesla", "model s");
        Car car2 = new Car("audi", "a4");
        Car car3 = new Car("toyota", "ayris");
        assertThat(carService.getAllCars()).hasSize(3).extracting(Car::getMaker).contains(car1.getMaker(), car2.getMaker(), car3.getMaker());
        verifyFindAllCarsIsCalledOnce();
    }

    private void verifyFindByIdIsCalledOnce(Long id) {
        Mockito.verify(carRepository, VerificationModeFactory.times(1)).findByCarId(id);
        //Mockito.reset(carRepository);
    }

    private void verifyFindAllCarsIsCalledOnce() {
        Mockito.verify(carRepository, VerificationModeFactory.times(1)).findAll();
        //Mockito.reset(carRepository);
    }
}
