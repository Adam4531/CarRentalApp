package pl.zetosoftware.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.zetosoftware.car.dto.CarDto;
import pl.zetosoftware.car.exception.CarNotFoundException;

import java.util.List;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    @Autowired
    public CarService(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    public CarDto addCar(Car car){
        carRepository.save(car);
        return carMapper.mapCarToCarDto(car);
    }

    public CarDto updateCar(Car car){
        carRepository.save(car);
        return carMapper.mapCarToCarDto(car);
    }

    public String deleteCarById(Long id){
        carRepository.deleteById(id);
        return("Car with id: " + id + " has been deleted successfully ");
    }

    public List<CarDto> getAllCars(){
        List<Car> cars = carRepository.findAll(Sort.by(
                Sort.Order.asc("brand"),
                Sort.Order.asc("model")));
        return carMapper.mapCarListToCarListDto(cars);
    }

    public CarDto findCarById(Long id){
        Car car = carRepository
                .findById(id)
                .orElseThrow( () -> new CarNotFoundException("Car with id " + id + " was not found. "));
        return carMapper.mapCarToCarDto(car);
    }

}
