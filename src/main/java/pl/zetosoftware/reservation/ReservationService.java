package pl.zetosoftware.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zetosoftware.reservation.dto.ReservationDto;
import pl.zetosoftware.reservation.value_objects.ReservationDatesValidator;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

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
    public boolean checkIfCarIsReserved(ReservationDatesValidator reservationDatesToCheck, Long Id){
        ReservationDatesValidator reservationOfCar = getReservation(Id).getDate();

        if(reservationDatesToCheck.equals(reservationOfCar)){ //are same
            return true;
        }
        if(reservationOfCar.dateStart.isBefore(reservationDatesToCheck.dateStart)
                || reservationOfCar.dateEnd.isAfter(reservationDatesToCheck.dateStart)
        ){
            return true;
        }
        return false;
    }
}



