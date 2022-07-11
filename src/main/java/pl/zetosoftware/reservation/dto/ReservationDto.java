package pl.zetosoftware.reservation.dto;

import lombok.Builder;
import pl.zetosoftware.reservation.enums.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ReservationDto(Long userId, Long carId, LocalDateTime dateStart, LocalDateTime dateEnd, BigDecimal cost, BigDecimal paymentInAdvance, Status status) {

    @Builder
    public ReservationDto {}

}
