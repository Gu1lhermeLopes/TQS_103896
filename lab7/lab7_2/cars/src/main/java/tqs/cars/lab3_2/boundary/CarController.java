package tqs.cars.lab3_2.boundary;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tqs.cars.lab3_2.model.Car;
import tqs.cars.lab3_2.service.CarService;

@RestController
@RequestMapping("/api")
public class CarController {
    final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/cars") 
    public ResponseEntity<Car> createCar(@RequestBody Car car){
        HttpStatus status = HttpStatus.CREATED;
        Car savedCar = carService.save(car);
        return new ResponseEntity<>(savedCar, status);
    }

    @GetMapping(path = "/cars")
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }   

    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable("id") Long id) {
        Car car = carService.getCarDetails(id).orElse(null);
        if (car == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    
}
