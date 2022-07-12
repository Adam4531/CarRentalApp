package pl.zetosoftware.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zetosoftware.reservation.dto.ReservationDto;

import java.util.Collection;
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
}
