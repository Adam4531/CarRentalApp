package pl.zetosoftware.reservation;

import org.springframework.stereotype.Component;
import pl.zetosoftware.reservation.dto.ReservationDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReservationMapper {

    public ReservationDto fromReservationToReservationDto(ReservationEntity reservationEntity){
        return ReservationDto.builder()
                .userId(reservationEntity.getUserId())
                .carId(reservationEntity.getCarId())
                .dateStart(reservationEntity.getDateStart())
                .dateEnd(reservationEntity.getDateEnd())
                .cost(reservationEntity.getCost())
                .paymentInAdvance(reservationEntity.getPaymentInAdvance())
                .status(reservationEntity.getStatus())
                .build();
    }

    public List<ReservationDto> fromReservationListToReservationDtoList(List<ReservationEntity> reservationEntityList){
        return reservationEntityList.stream()
                .map(this::fromReservationToReservationDto)
                .collect(Collectors.toList());
    }
}
