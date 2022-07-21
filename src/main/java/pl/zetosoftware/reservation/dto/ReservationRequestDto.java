package pl.zetosoftware.reservation.dto;

import lombok.Builder;

import java.time.LocalDate;

//Long userId,
// OPCJONALNIE DO ZMIANY NA userId ZAMIAST EMAIL
public record ReservationRequestDto(String email, Long carId, LocalDate dateStart, LocalDate dateEnd) {

    @Builder public ReservationRequestDto{}
}
