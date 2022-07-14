package pl.zetosoftware.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.zetosoftware.car.dto.CarDto;
import pl.zetosoftware.car.exception.CarNotFoundException;
import pl.zetosoftware.car.value_objects.ProductionYearValidator;
import pl.zetosoftware.reservation.ReservationEntity;

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

    public CarDto addCar(CarEntity carEntity) {
        carRepository.save(carEntity);
        return carMapper.mapCarToCarDto(carEntity);
    }

    public CarEntity getCarEntityById(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException("Car with id " + id + " was not found. "));
    }

    public CarDto findCarById(Long id) {
        CarEntity carEntity = getCarEntityById(id);
        return carMapper.mapCarToCarDto(carEntity);
    }

    public CarDto updateCar(Long id, CarEntity carEntity) {
        var car = getCarEntityById(id);
        carRepository.save(carEntity);
        return carMapper.mapCarToCarDto(carEntity);
    }

    public void deleteCarById(Long id) {
        carRepository.deleteById(id);
    }

    public List<CarDto> getAllCars() {
        List<CarEntity> carEntities = carRepository.findAll(Sort.by(
                Sort.Order.asc("brand"),
                Sort.Order.asc("model")));
        return carMapper.mapCarListToCarListDto(carEntities);
    }
    //lista samochodów, status samochodu
    //to ma być wyświetlone: (czyli nowy DTO)
    //np. volkswagen, golf, 2019, czerwony, cena za dzień(null), zarezerwowany (status), ...

    /////////////////////
    //id, marka, model itd.
    //id samochodu w rezerwacji
    //a w rezerwacji używając id samochodu porównać date dzisiejszą i daty wypożyczenia
    public void updateStatus(Long Id){

//        carRepository.findAll().stream().map(CarEntity::getStatus).
        var rez = new ReservationEntity();
        if(rez.getStartDate().isBefore(LocalDate.now()) && rez.getEndDate().isAfter(LocalDate.now())){
            // ListaSamochodowDTO.setStatus("zarezerwowany");
        } else {
            // ListaSamochodowDTO.setStatus("aktywny");
        }
    }

    public BigDecimal productionYearFactor(CarEntity car) {
        if (isBetween(car.getProductionYear(), LocalDate.now().minusYears(2), LocalDate.now())) {
            return BigDecimal.valueOf(0.15);
        }
        if(isBetween(car.getProductionYear(), LocalDate.now().minusYears(8), LocalDate.now().minusYears(3))) {
            return BigDecimal.valueOf(0.10);
        }
        if(isBetween(car.getProductionYear(), LocalDate.now().minusYears(12), LocalDate.now().minusYears(9))) {
            return BigDecimal.valueOf(0.07);
        }
        if(isBetween(car.getProductionYear(), LocalDate.now().minusYears(22), LocalDate.now().minusYears(13))) {
            return BigDecimal.valueOf(0.05);
        }

        return BigDecimal.ZERO;
    }

    private boolean isBetween(ProductionYearValidator productionYear, LocalDate lower, LocalDate upper) {
        return lower.getYear() <= productionYear.toInteger() && productionYear.toInteger() <= upper.getYear();
    }
}
