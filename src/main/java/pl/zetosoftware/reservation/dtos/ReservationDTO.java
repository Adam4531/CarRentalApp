package pl.zetosoftware.reservation.dtos;

import lombok.Builder;
import lombok.Getter;
import pl.zetosoftware.reservation.enums.Status;
import pl.zetosoftware.reservation.value_objects.Cost;

import java.time.LocalDateTime;

@Getter
@Builder
public class ReservationDTO {
    private Long userId;
    private Long carId;
    private LocalDateTime dateStart;
    private LocalDateTime dateEnd;
    private Cost cost;
    private Status status;
}
