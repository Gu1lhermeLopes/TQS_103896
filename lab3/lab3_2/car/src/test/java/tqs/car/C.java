package tqs.car;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import tqs.car.data.*;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;



@DataJpaTest
public class C {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository carRepository;

    @Test
    void whenFindCarById_thenReturnCar() {
        // arrange a new employee and insert into db
        Car a = new Car("audi", "a4");
        entityManager.persistAndFlush(a); //ensure data is persisted at this point

        // test the query method of interest
        Car found = carRepository.findByCarId(a.getCarId());
        assertThat( found ).isEqualTo(a);
    }

    @Test
    void whenInvalidId_thenReturnNull() {
        Car car = carRepository.findByCarId(-1L);
        assertThat(car).isNull();
    }

    @Test
    public void givenSetOfCars_whenFindAll_thenReturnAllCars() {
        Car a = new Car("audi", "a4");
        Car b = new Car("bmw", "i8");
        Car c = new Car("chervolet", "camaro");

        entityManager.persist(a);
        entityManager.persist(b);
        entityManager.persist(c);
        entityManager.flush();

        List<Car> list = carRepository.findAll();

        assertThat(list).hasSize(3).extracting(Car::getMaker).containsOnly(a.getMaker(), b.getMaker(), c.getMaker());
    }
}
