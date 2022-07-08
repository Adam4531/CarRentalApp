package pl.zetosoftware.reservation.dtos;

import lombok.Getter;
import pl.zetosoftware.reservation.value_objects.Cost;
import pl.zetosoftware.reservation.value_objects.PaymentInAdvance;

import java.time.LocalDateTime;

@Getter
public class CreateReservationDTO {
    private Long userId;
    private Long carId;
    private LocalDateTime dateStart;
    private LocalDateTime dateEnd;
    private Cost cost;
    private PaymentInAdvance paymentInAdvance;
}
