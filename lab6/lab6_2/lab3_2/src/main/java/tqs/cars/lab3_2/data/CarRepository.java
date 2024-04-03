package tqs.cars.lab3_2.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tqs.cars.lab3_2.model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>{
    public Car findByCarId(Long carId);
    public List<Car> findAll();
    
}
