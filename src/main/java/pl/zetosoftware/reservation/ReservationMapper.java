package pl.zetosoftware.reservation;

import org.springframework.stereotype.Component;
import pl.zetosoftware.reservation.dtos.ReservationDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReservationMapper {

    public ReservationDto fromReservationToReservationDto(Reservation reservation){
        return ReservationDto.builder()
                .userId(reservation.getUserId())
                .carId(reservation.getCarId())
                .dateStart(reservation.getDateStart())
                .dateEnd(reservation.getDateEnd())
                .cost(reservation.getCost())
                .paymentInAdvance(reservation.getPaymentInAdvance())
                .status(reservation.getStatus())
                .build();
    }

    public List<ReservationDto> fromReservationListToReservationDtoList(List<Reservation> reservationList){
        return reservationList.stream()
                .map(this::fromReservationToReservationDto)
                .collect(Collectors.toList());
    }
}
