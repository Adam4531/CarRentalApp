package pl.zetosoftware.reservation;

import org.springframework.stereotype.Component;
import pl.zetosoftware.reservation.dto.ReservationDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReservationMapper {

    public ReservationDto fromReservationToReservationDto(ReservationEntity reservationEntity){
        return ReservationDto.builder()
                .userId(reservationEntity.getUserId().getId())
                .carId(reservationEntity.getCarId().getId())
                .dateStart(reservationEntity.getDate().getDateStart())
                .dateEnd(reservationEntity.getDate().getDateEnd())
                .cost(reservationEntity.getCost().getCost())
                .paymentInAdvance(reservationEntity.getPaymentInAdvance().getPaymentInAdvance())
                .build();
    }

    public List<ReservationDto> fromReservationListToReservationDtoList(List<ReservationEntity> reservationEntityList){
        return reservationEntityList.stream()
                .map(this::fromReservationToReservationDto)
                .collect(Collectors.toList());
    }

}
