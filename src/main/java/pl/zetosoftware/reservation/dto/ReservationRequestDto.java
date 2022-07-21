package pl.zetosoftware.reservation.dto;

import lombok.Builder;

import java.time.LocalDate;

public record ReservationRequestDto(Long userId, Long carId, LocalDate dateStart, LocalDate dateEnd) {

    @Builder public ReservationRequestDto{}
}
