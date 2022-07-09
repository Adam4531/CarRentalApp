package pl.zetosoftware.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zetosoftware.car.exception.CarNotFoundException;

import java.util.List;

@Service
public class CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car addCar(Car car){
        return carRepository.save(car);
    }

    public Car updateCar(Car car){
        return carRepository.save(car);
    }

    public void deleteCarById(Long id){
        carRepository.deleteById(id);
    }

    public List<Car> findAllCars(){
        return carRepository.findAll();
    }

    public Car findCarById(Long id){
        return carRepository.findById(id).orElseThrow( () -> new CarNotFoundException("Car by id " + id + " was not found"));
    }

}
