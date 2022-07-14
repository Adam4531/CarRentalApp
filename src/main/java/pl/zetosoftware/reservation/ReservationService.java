package pl.zetosoftware.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zetosoftware.car.CarEntity;
import pl.zetosoftware.car.CarService;
import pl.zetosoftware.reservation.dto.ReservationDto;

import java.math.BigDecimal;
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

    public ReservationDto getReservationByReservationId(Long Id) {
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

    private List<ReservationDto> getAllReservationsByCarId(Long id){
        List<ReservationEntity> allReservationsByCarId = reservationRepository.getAllReservationsByCarId(id);
        return reservationMapper.fromReservationListToReservationDtoList(allReservationsByCarId);
    }
    public BigDecimal popularityOfCar(Long id){
        int numberOfReservationsOfTheMostPopularCar = getNumberOfReservationsOfTheMostPopularCar();
        int numberOfReservationsOfTheSelectedCar = getAllReservationsByCarId(id).size();
        if(numberOfReservationsOfTheMostPopularCar == numberOfReservationsOfTheSelectedCar){
            return BigDecimal.valueOf(3);
        }
        if(numberOfReservationsOfTheMostPopularCar > numberOfReservationsOfTheSelectedCar &&
                numberOfReservationsOfTheSelectedCar > numberOfReservationsOfTheMostPopularCar / 2){
            return BigDecimal.valueOf(2);
        }
        return BigDecimal.ONE;
    }

    private int getNumberOfReservationsOfTheMostPopularCar() {
        return reservationRepository.getAllCarsByPopularityOfReservations().values().stream().mapToInt(i -> i).max()
                .orElseThrow(NoSuchElementException::new);
    }

    public BigDecimal initialPrice(Long id) {
        CarEntity car = carService.getCarEntityById(id);
        return BigDecimal.valueOf(car.getNewCarCost().toLong())
                .multiply(BigDecimal.valueOf(0.001))
                .multiply(carService.productionYearFactor(car))
                .multiply(popularityOfCar(id));
    }

    public BigDecimal setPrice(Long id, Integer days) {
        return initialPrice(id).multiply(BigDecimal.valueOf(days));
    }

}
