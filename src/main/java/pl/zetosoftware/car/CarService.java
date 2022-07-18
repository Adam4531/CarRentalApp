package pl.zetosoftware.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zetosoftware.car.dto.CarDto;
import pl.zetosoftware.car.dto.ReservationCarDto;
import pl.zetosoftware.car.enums.StatusEnum;
import pl.zetosoftware.car.exception.CarNotFoundException;
import pl.zetosoftware.car.value_objects.ProductionYearValidator;
import pl.zetosoftware.reservation.ReservationEntity;
import pl.zetosoftware.reservation.ReservationService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    private final ReservationService reservationService;

    @Autowired
    public CarService(CarRepository carRepository, CarMapper carMapper, ReservationService reservationService) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
        this.reservationService = reservationService;
    }

    public CarDto addCar(CarEntity carEntity) {
        carRepository.save(carEntity);
        return carMapper.mapCarToCarDto(carEntity);
    }

    private CarEntity getCarEntityById(Long id) {
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


    public List<ReservationCarDto> getAllCars(){ //TODO metoda dla controller'a? TAK, zamienić z getAllCars i pozmieniać kolumny na frontendzie
        List<CarEntity> carEntityList = carRepository.findAll();
        List<ReservationCarDto> carDtoList = new ArrayList<>();
        for (CarEntity carEntity: carEntityList) {
            ReservationCarDto reservationCarDto = ReservationCarDto.builder()
                    .model(carEntity.getModel().toString())
                    .brand(carEntity.getBrand().toString())
                    .engineCapacity(carEntity.getEngineCapacity().toBigDecimal())
                    .productionYear(carEntity.getProductionYear().toInteger())
                    .pricePerDayRent(setInitialPrice(carEntity.getId()))
                    .bodyTypeEnum(carEntity.getBodyType())
                    .status(getStatus(carEntity.getId()))
                    .build();
            carDtoList.add(reservationCarDto);
        }
        carDtoList.sort(Comparator.comparing(ReservationCarDto::getBrand).thenComparing(ReservationCarDto::getModel));

        return carDtoList;
    }

    public StatusEnum getStatus(Long CarId) {
        List<ReservationEntity> allReservationsById = carRepository.getAllReservationsByCarId(CarId);

        for (ReservationEntity reservationEntity : allReservationsById) {
            if (!Objects.isNull(reservationEntity)) {
                if (reservationEntity.getStartDate().isBefore(LocalDate.now()) && reservationEntity.getEndDate().isAfter(LocalDate.now())) {
                    return StatusEnum.RESERVED;
                }
            }
            return StatusEnum.FREE;
        }
        return StatusEnum.FREE;
    }

    public BigDecimal setInitialPrice(Long id) {
        CarEntity car = carRepository.getReferenceById(id);
        return BigDecimal.valueOf(car.getNewCarCost().toLong())
                .multiply(BigDecimal.valueOf(0.001))
                .multiply(productionYearFactor(car));
    }

    public BigDecimal setPricePerDays(Long Id, Integer days) {
        return setInitialPrice(Id).multiply(BigDecimal.valueOf(days));
    }

    public BigDecimal popularityOfCar(Long Id) {
        int numberOfReservationOfTheMostPopularCar = reservationService.getNumberOfReservationsOfTheMostPopularCar();
        int numberOfReservationsOfTheSelectedCar = reservationService.getAllReservationsByCarId(Id).size();

        if (numberOfReservationsOfTheSelectedCar == numberOfReservationOfTheMostPopularCar) {
            return BigDecimal.valueOf(3);
        }
        if (numberOfReservationOfTheMostPopularCar > numberOfReservationsOfTheSelectedCar &&
                numberOfReservationsOfTheSelectedCar > numberOfReservationOfTheMostPopularCar / 2) {
            return BigDecimal.valueOf(2);
        }

        return BigDecimal.ONE;
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

}
