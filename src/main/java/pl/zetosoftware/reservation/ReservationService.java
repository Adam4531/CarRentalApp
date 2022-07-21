package pl.zetosoftware.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zetosoftware.reservation.dto.ReservationDto;
import pl.zetosoftware.user.value_objects.EmailValidator;

import java.time.LocalDate;
import java.util.*;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    private final ReservationValidator reservationEditor;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, ReservationMapper reservationMapper, ReservationValidator reservationEditor) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
        this.reservationEditor = reservationEditor;
    }

    public ReservationDto createReservation(ReservationEntity reservationEntity) {
        return reservationMapper.fromReservationToReservationDto(reservationRepository.save(reservationEntity));
    }

    public String deleteReservationById(Long id) {
        var reservation = getReservation(id);
        if (!reservationEditor.isPresentDayBeforeReservationToChange(reservation))
            throw new IllegalStateException("Not allowed to delete started/completed reservations!");
        reservationRepository.delete(reservation);
        return "Reservation with id:" + id + " deleted successfully";

    }

    public ReservationDto changeReservationDatesByReservationId(Long id, LocalDate dateStart, LocalDate dateEnd) {
        var reservation = getReservation(id);
        if (!reservationEditor.isReservationAvailable(reservation, dateStart, dateEnd))
            throw new IllegalStateException("Other reservation is in progress during this period!");
        reservation.changeReservationDates(dateStart, dateEnd);
        reservationRepository.save(reservation);
        return reservationMapper.fromReservationToReservationDto(reservation);
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

    public List<ReservationDto> getAllReservationsByEmail(Long Id) {
        List<ReservationEntity> allReservationsByUserId = reservationRepository.getAllReservationsByUserId(Id);
        return reservationMapper.fromReservationListToReservationDtoList(allReservationsByUserId);
    }

    public List<ReservationDto> getAllReservationsByEmail(String email) {
        EmailValidator emailValidator = new EmailValidator(email);
        List<ReservationEntity> allReservationsByEmail = reservationRepository.findAllByUserIdEmail(emailValidator);
        return reservationMapper.fromReservationListToReservationDtoList(allReservationsByEmail);
    }

    public List<ReservationDto> getAllReservationsByCarId(Long id){
        List<ReservationEntity> allReservationsByCarId = reservationRepository.getAllReservationsByCarId(id);
        return reservationMapper.fromReservationListToReservationDtoList(allReservationsByCarId);
    }

    public int getNumberOfReservationsOfTheMostPopularCar() {
        return reservationRepository.getCarsWithNumberOfReservations().values().stream().mapToInt(i -> i).max()
                .orElseThrow(NoSuchElementException::new);
    }

}
