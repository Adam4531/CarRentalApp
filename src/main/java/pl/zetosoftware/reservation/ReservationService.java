package pl.zetosoftware.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zetosoftware.car.CarEntity;
import pl.zetosoftware.car.CarService;
import pl.zetosoftware.reservation.dto.ReservationCarDto;
import pl.zetosoftware.reservation.dto.ReservationDto;
import pl.zetosoftware.reservation.value_objects.ReservationDatesValidator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;
    @Autowired
    private CarService carService;

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

    public List<ReservationDto> getAllReservationsById(Long Id) {
        List<ReservationEntity> allReservationsByUserId = reservationRepository.getAllReservationsByUserId(Id);
        return reservationMapper.fromReservationListToReservationDtoList(allReservationsByUserId);
    }

    //TODO check if it is not supposed to return String, true - its resevered, false - its free
    //  reserved     11-14, 13-21,
    //  next client  12-15, 11-14,
    //  and make tests
    public String checkStatus(ReservationDatesValidator reservationDatesToCheck, Long Id) {
        ReservationDatesValidator reservationOfCar = getReservation(Id).getDate();

        if (reservationDatesToCheck.equals(reservationOfCar)) {
            return "RESERVED";
        }
        if (reservationOfCar.dateStart.isBefore(reservationDatesToCheck.dateStart)
                || reservationOfCar.dateEnd.isAfter(reservationDatesToCheck.dateStart)
        ) {
            return "RESERVED";
        }
        return "FREE";
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

    private List<ReservationDto> getAllReservationsByCarId(Long id) {
        List<ReservationEntity> allReservationsByCarId = reservationRepository.getAllReservationsByCarId(id);
        return reservationMapper.fromReservationListToReservationDtoList(allReservationsByCarId);
    }

    private int getNumberOfReservationsOfTheMostPopularCar() {
        return reservationRepository.getAllCarsByPopularityOfReservations().values().stream().mapToInt(i -> i).max()
                .orElseThrow(NoSuchElementException::new);
    }

    public BigDecimal setPrice(Long id, Integer days) {
        CarEntity car = carService.getCarEntityById(id);
        return BigDecimal.valueOf(car.getNewCarCost().toLong())
                .multiply(BigDecimal.valueOf(0.001))
                .multiply(carService.productionYearFactor(car))
                .multiply(BigDecimal.valueOf(days));
    }

    //osobno dla każdego czy od razu update dla wszystkich
    public void updateStatus(Long id) {    //String or StatusEnum
        var reservation = new ReservationEntity();

        ReservationEntity car = getReservation(id);

        if (reservation.getStartDate().isBefore(LocalDate.now()) && reservation.getEndDate().isAfter(LocalDate.now())) {
            // ListaSamochodowDTO.setStatus("zarezerwowany");
        } else {
            // ListaSamochodowDTO.setStatus("aktywny");
        }
    }

    public void updateStatusForAllCars() {
//        List<ReservationCarDto> reservationCars =
//                reservationMapper.fromCarDtoListToReservationCarDtoList(carService.getAllCars());

        List<ReservationEntity> reservations = findAllReservations();

//        for (ReservationCarDto reservationCarDto: reservations){
//            if (reservation.getStartDate().isBefore(LocalDate.now()) && reservation.getEndDate().isAfter(LocalDate.now())) {
//                 ListaSamochodowDTO.setStatus("zarezerwowany");
//            } else {
//                 ListaSamochodowDTO.setStatus("aktywny");
//            }
    }

}
