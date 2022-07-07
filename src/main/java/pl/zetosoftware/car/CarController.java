package pl.zetosoftware.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cars")
//@CrossOrigin(origins = "http://localhost:4200")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    //upload
    @GetMapping("/all")
    public ResponseEntity<List<Car>> getAllCars (){
        List<Car> cars = carService.findAllCars();
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Car> getCarById (@PathVariable("id") Long id){
        Car car = carService.findCarById(id);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    //edit
    @PostMapping("/add")
    public ResponseEntity<Car> addCar(@RequestBody Car car){
        Car newCar = carService.addCar(car);
        return new ResponseEntity<>(newCar, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Car> updateCar(@RequestBody Car car){
        Car updatedCar = carService.updateCar(car);
        return new ResponseEntity<>(updatedCar, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable("id") Long id){
        carService.deleteCarById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }




}
