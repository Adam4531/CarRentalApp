package pl.zetosoftware.reservation.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;


public record ReservationDto(Long userId, Long carId, LocalDate dateStart, LocalDate dateEnd, BigDecimal cost, BigDecimal paymentInAdvance) {
    @Builder
    public ReservationDto {}

}
