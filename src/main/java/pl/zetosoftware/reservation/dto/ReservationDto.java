package pl.zetosoftware.reservation.dto;

import lombok.Builder;
import pl.zetosoftware.annotations.GenerateTypescript;
import pl.zetosoftware.reservation.enums.Status;
import pl.zetosoftware.reservation.value_objects.Cost;
import pl.zetosoftware.reservation.value_objects.PaymentInAdvance;

import java.time.LocalDateTime;

@GenerateTypescript
public record ReservationDto(Long userId, Long carId, LocalDateTime dateStart, LocalDateTime dateEnd, Cost cost, PaymentInAdvance paymentInAdvance, Status status) {

    @Builder
    public ReservationDto {}

}
