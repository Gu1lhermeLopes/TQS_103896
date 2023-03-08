package tqs.car.boundary;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tqs.car.service.CarManagerService;
import tqs.car.data.*;;

@RestController
@RequestMapping("/api")
public class CarController {

    private final CarManagerService service;
    public CarController(CarManagerService service) {
        this.service = service;
    }

    @GetMapping(path="/cars" )
    public List<Car> getAllCars() {
        return service.getAllCars();
    }

    @PostMapping(path="/cars")
    public ResponseEntity<Car> createCar(@RequestBody Car car){
        HttpStatus status = HttpStatus.CREATED;
        Car saved = service.save( car);
        return new ResponseEntity<>(saved, status);
    }

    @GetMapping(path="/cars/{id}" )
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        Car car =service.getCarDetails(id).get();
        return ResponseEntity.ok().body(car);
    }
    
}
