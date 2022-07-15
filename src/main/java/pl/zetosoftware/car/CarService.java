package pl.zetosoftware.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.zetosoftware.car.dto.CarDto;
import pl.zetosoftware.car.enums.StatusEnum;
import pl.zetosoftware.car.exception.CarNotFoundException;
import pl.zetosoftware.car.value_objects.ProductionYearValidator;
import pl.zetosoftware.reservation.ReservationService;
import pl.zetosoftware.reservation.dto.ReservationCarDto;
import pl.zetosoftware.reservation.dto.ReservationDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    @Autowired
    private ReservationService reservationService;

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

    public List<ReservationCarDto> getAllCarsToShow() {
        List<CarEntity> carEntities = carRepository.findAll(Sort.by(
                Sort.Order.asc("brand"),
                Sort.Order.asc("model")));
        return carMapper.fromCarEntityListToReservationCarDtoList(carEntities);
    }

    public BigDecimal productionYearFactor(CarEntity car) {
        if (isBetween(car.getProductionYear(), LocalDate.now().minusYears(2), LocalDate.now())) {
            return BigDecimal.valueOf(0.15);
        }
        if (isBetween(car.getProductionYear(), LocalDate.now().minusYears(8), LocalDate.now().minusYears(3))) {
            return BigDecimal.valueOf(0.10);
        }
        if (isBetween(car.getProductionYear(), LocalDate.now().minusYears(12), LocalDate.now().minusYears(9))) {
            return BigDecimal.valueOf(0.07);
        }
        if (isBetween(car.getProductionYear(), LocalDate.now().minusYears(22), LocalDate.now().minusYears(13))) {
            return BigDecimal.valueOf(0.05);
        }

        return BigDecimal.ONE;
    }

    private boolean isBetween(ProductionYearValidator productionYear, LocalDate lower, LocalDate upper) {
        return lower.getYear() <= productionYear.toInteger() && productionYear.toInteger() <= upper.getYear();
    }

    public StatusEnum refreshStatus(Long CarId) {
        List<ReservationDto> allReservationsById = reservationService.getAllReservationsByCarId(CarId);

        for (ReservationDto reservationDto : allReservationsById) {
            if (!Objects.isNull(reservationDto)) {
                if (reservationDto.dateStart().isBefore(LocalDate.now()) && reservationDto.dateEnd().isAfter(LocalDate.now())) {
                    return StatusEnum.RESERVED;
                } else {
                    return StatusEnum.FREE;
                }
            } else {
                return StatusEnum.FREE;
            }
        }
        return StatusEnum.FREE;
    }

    public BigDecimal initialPrice(Long id) {
        CarEntity car = getCarEntityById(id);
        return BigDecimal.valueOf(car.getNewCarCost().toLong())
                .multiply(BigDecimal.valueOf(0.001))
                .multiply(productionYearFactor(car));
    }

    public BigDecimal setPrice(Long Id, Integer days) {
        return initialPrice(Id).multiply(BigDecimal.valueOf(days));
    }
    
}
