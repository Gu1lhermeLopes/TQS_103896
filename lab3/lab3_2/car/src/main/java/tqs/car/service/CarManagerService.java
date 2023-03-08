package tqs.car.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tqs.car.data.*;

@Service
public class CarManagerService {

    @Autowired
    private CarRepository carRepository;





    public Optional<Car> getCarDetails(Long id) {
        Car c = carRepository.findByCarId(id);
        return Optional.ofNullable(c);
    }



    public Car save(Car employee) {
        return carRepository.save(employee);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }
    
}
