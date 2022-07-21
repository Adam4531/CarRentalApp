package pl.zetosoftware.reservation;

import org.springframework.stereotype.Service;
import pl.zetosoftware.global.dto.ErrorsListDto;
import pl.zetosoftware.reservation.dto.ReservationRequestDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

@Service
public class ReservationCreateService {

    private final ReservationWebMapper reservationWebMapper;

    private final ReservationRepository reservationRepository;

    public ReservationCreateService(ReservationWebMapper reservationWebMapper, ReservationRepository reservationRepository) {
        this.reservationWebMapper = reservationWebMapper;
        this.reservationRepository = reservationRepository;
    }

    public ErrorsListDto create(ReservationRequestDto reservationRequestDto) {

        ErrorsListDto errorsListDto = new ErrorsListDto( new ArrayList<>() );

        if(Objects.isNull(reservationRequestDto.dateStart())){
            errorsListDto.add("Date start can't be null!!");
        }

        if(Objects.isNull(reservationRequestDto.dateEnd())){
            errorsListDto.add("Date end can't be null!");
        }

        if ( !isPresentDayBeforeReservation(reservationRequestDto)){
            errorsListDto.add("Reservation must start at least 1 day after the present day!");
        }

        if ( reservationRequestDto.dateStart().isAfter(reservationRequestDto.dateEnd())){
            errorsListDto.add("Date of start must be before the date of end reservation!");
        }

        if (reservationRequestDto.dateStart().equals(reservationRequestDto.dateEnd())) {
            errorsListDto.add("Minimum reservation length is 1 day!");
        }

//        DODAC WALIDACJE SPRAWDZAJACA CZY NIE WYSTEPUJA KOLIZJE MIEDZY REZERWACJAMI

        if (errorsListDto.isListOfErrorsEmpty()) {
            var reservationEntity = reservationWebMapper.fromReservationRequestDtoToReservationEntity(reservationRequestDto);
            reservationRepository.save(reservationEntity);
        }
        return errorsListDto;
    }
    public boolean isPresentDayBeforeReservation (ReservationRequestDto reservation){
        return LocalDate.now().isBefore(reservation.dateStart());
    }

}
