package pl.zetosoftware.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.zetosoftware.car.dto.CarDto;
import pl.zetosoftware.car.exception.CarNotFoundException;

import java.math.BigDecimal;
import java.time.LocalDate;
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

    public CarDto addCar(CarEntity carEntity){
        carRepository.save(carEntity);
        return carMapper.mapCarToCarDto(carEntity);
    }

    public CarEntity getCarEntityById(Long id) {
        return carRepository.findById(id)
                .orElseThrow( () -> new CarNotFoundException("Car with id " + id + " was not found. "));
    }

    public CarDto findCarById(Long id){
        CarEntity carEntity = getCarEntityById(id);
        return carMapper.mapCarToCarDto(carEntity);
    }

    public CarDto updateCar(Long id, CarEntity carEntity){
        var car = getCarEntityById(id);
        carRepository.save(carEntity);
        return carMapper.mapCarToCarDto(carEntity);
    }

    public void deleteCarById(Long id){
        carRepository.deleteById(id);
    }

    public List<CarDto> getAllCars(){
        List<CarEntity> carEntities = carRepository.findAll(Sort.by(
                Sort.Order.asc("brand"),
                Sort.Order.asc("model")));
        return carMapper.mapCarListToCarListDto(carEntities);
    }

    public BigDecimal setPrice(Long id) {
        CarEntity car = getCarEntityById(id);
        BigDecimal price = BigDecimal.valueOf(car.getNewCarCost().toLong()).multiply(BigDecimal.valueOf(0.001)).multiply(productionYearFactor());

        return null;
    }

    private BigDecimal productionYearFactor() {
        LocalDate.now().minusYears(2);
//        switch (){

//        }
        return null;
    }


}
