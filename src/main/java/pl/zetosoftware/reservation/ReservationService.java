package pl.zetosoftware.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zetosoftware.reservation.dto.ReservationDto;

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

    public Reservation createReservation(Reservation reservation){
        return reservationRepository.save(reservation);
    }

    public List<ReservationDto> getAllReservations(){
        List<Reservation> reservations = reservationRepository.findAll();
        return reservationMapper.fromReservationListToReservationDtoList(reservations);
    }

    public Reservation getReservation(Long Id){
        return reservationRepository.findById(Id)
                .orElseThrow(() -> new NoSuchElementException("Reservation with id: " + Id + " not found!"));
    }

    public ReservationDto getReservationById(Long Id){
        Reservation reservation = getReservation(Id);
        return  reservationMapper.fromReservationToReservationDto(reservation);
    }

    public List<Reservation> findAllReservations(){
        return reservationRepository.findAll();
    }
}
