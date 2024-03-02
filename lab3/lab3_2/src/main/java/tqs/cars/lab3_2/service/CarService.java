package tqs.cars.lab3_2.service;
import tqs.cars.lab3_2.model.Car;
import java.util.List;
import java.util.Optional;


public interface CarService {
    public Optional<Car> getCarDetails(Long carId);
    public Car save(Car car);
    public List<Car> getAllCars();
}
