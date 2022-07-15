package pl.zetosoftware.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.zetosoftware.car.CarEntity;
import pl.zetosoftware.car.CarRepository;
import pl.zetosoftware.car.enums.StatusEnum;
import pl.zetosoftware.car.value_objects.ProductionYearValidator;
import pl.zetosoftware.reservation.dto.ReservationCarDto;
import pl.zetosoftware.reservation.dto.ReservationDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    @Autowired
    public CarRepository carRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, ReservationMapper reservationMapper) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
    }

    public ReservationDto createReservation(ReservationEntity reservationEntity) {
        return reservationMapper.fromReservationToReservationDto(reservationRepository.save(reservationEntity));
    }

    public String deleteReservationById(Long Id) {
        reservationRepository.deleteById(Id);
        return "Reservation with id:" + Id + " deleted successfully";
    }

    public List<ReservationDto> getAllReservations() {
        List<ReservationEntity> reservationEntities = reservationRepository.findAll();
        return reservationMapper.fromReservationListToReservationDtoList(reservationEntities);
    }

    public ReservationEntity getReservation(Long Id) {
        return reservationRepository.findById(Id)
                .orElseThrow(() -> new NoSuchElementException("ReservationEntity with id: " + Id + " not found!"));
    }

    public ReservationDto getReservationById(Long Id) {
        ReservationEntity reservationEntity = getReservation(Id);
        return reservationMapper.fromReservationToReservationDto(reservationEntity);
    }

    public List<ReservationEntity> findAllReservations() {
        return reservationRepository.findAll();
    }

    public List<ReservationDto> getAllReservationsByUserId(Long Id) {
        List<ReservationEntity> allReservationsByUserId = reservationRepository.getAllReservationsByUserId(Id);
        return reservationMapper.fromReservationListToReservationDtoList(allReservationsByUserId);
    }

    public BigDecimal popularityOfCar(Long Id) {
        int numberOfReservationOfTheMostPopularCar = getNumberOfReservationsOfTheMostPopularCar();
        int numberOfReservationsOfTheSelectedCar = getAllReservationsByCarId(Id).size();

        if (numberOfReservationsOfTheSelectedCar == numberOfReservationOfTheMostPopularCar) {
            return BigDecimal.valueOf(3);
        }
        if (numberOfReservationOfTheMostPopularCar > numberOfReservationsOfTheSelectedCar &&
                numberOfReservationsOfTheSelectedCar > numberOfReservationOfTheMostPopularCar / 2) {
            return BigDecimal.valueOf(2);
        }

        return BigDecimal.ONE;
    }

    public List<ReservationDto> getAllReservationsByCarId(Long id) {
        List<ReservationEntity> allReservationsByCarId = reservationRepository.getAllReservationsByCarId(id);
        return reservationMapper.fromReservationListToReservationDtoList(allReservationsByCarId);
    }

    private int getNumberOfReservationsOfTheMostPopularCar() {
        return reservationRepository.getAllCarsByPopularityOfReservations().values().stream().mapToInt(i -> i).max()
                .orElseThrow(NoSuchElementException::new);
    }

    public StatusEnum refreshStatus(Long CarId) {
        List<ReservationDto> allReservationsById = getAllReservationsByCarId(CarId);

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
        CarEntity car = carRepository.getReferenceById(id);
        return BigDecimal.valueOf(car.getNewCarCost().toLong())
                .multiply(BigDecimal.valueOf(0.001))
                .multiply(productionYearFactor(car));
    }

    public BigDecimal setPrice(Long Id, Integer days) {
        return initialPrice(Id).multiply(BigDecimal.valueOf(days));
    }

    public ReservationCarDto fromCarEntityToReservationCarDto(CarEntity carEntity){
        return ReservationCarDto.builder()
                .brand(carEntity.getBrand().toString())
                .model(carEntity.getModel().toString())
                .engineCapacity(carEntity.getEngineCapacity().toBigDecimal())
                .bodyTypeEnum(carEntity.getBodyType())
                .status(refreshStatus(carEntity.getId()))
                .pricePerDayRent(initialPrice(carEntity.getId()))
                .build();
    }

    public List<ReservationCarDto> fromCarEntityListToReservationCarDtoList(List<CarEntity> carEntityList){
        return carEntityList.stream()
                .map(this::fromCarEntityToReservationCarDto)
                .collect(Collectors.toList());
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

    public List<ReservationCarDto> getAllCarsToShow() {
        List<CarEntity> carEntities = carRepository.findAll(Sort.by(
                Sort.Order.asc("brand"),
                Sort.Order.asc("model")));
        return fromCarEntityListToReservationCarDtoList(carEntities);
    }

}
