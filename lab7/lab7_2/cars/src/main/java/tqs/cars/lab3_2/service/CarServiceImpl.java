package tqs.cars.lab3_2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import tqs.cars.lab3_2.data.CarRepository;
import tqs.cars.lab3_2.model.Car;

@Service
public class CarServiceImpl implements CarService {

    final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Optional<Car> getCarDetails(Long carId) {
        Car car = carRepository.findByCarId(carId);
        if (car == null) {
            return Optional.empty();
        }
        return Optional.of(car);
    }

    public Car save(Car car) {
        return carRepository.save(car);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }
    
    
}
