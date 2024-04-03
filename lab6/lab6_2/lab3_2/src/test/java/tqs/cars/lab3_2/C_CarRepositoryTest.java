package tqs.cars.lab3_2;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import tqs.cars.lab3_2.data.CarRepository;
import tqs.cars.lab3_2.model.Car;

@DataJpaTest
public class C_CarRepositoryTest {
    
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository carRepository;
    
    @Test
    void whenFindCarById_thenReturnCar() {
        Car car = new Car("tesla", "model s");
        entityManager.persistAndFlush(car);
        Car found = carRepository.findByCarId(car.getCarId());
        assertThat(found).isEqualTo(car);

    }

    @Test
    void whenInvalidCarId_thenReturnNull() {
        Car found = carRepository.findByCarId(-1L);
        assertThat(found).isNull();
    }

    @Test
    void whenFindAll_thenReturnAllCars() {
        Car car1 = new Car("tesla", "model s");
        Car car2 = new Car("audi", "a4");
        Car car3 = new Car("toyota", "ayris");
        entityManager.persist(car1);
        entityManager.persist(car2);
        entityManager.persist(car3);
        entityManager.flush();
        assertThat(carRepository.findAll()).hasSize(3).extracting(Car::getCarId).containsOnly(car1.getCarId(), car2.getCarId(), car3.getCarId());
    }



}
