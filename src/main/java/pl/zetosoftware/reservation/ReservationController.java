package pl.zetosoftware.reservation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.zetosoftware.reservation.dto.ReservationDto;
import pl.zetosoftware.reservation.value_objects.ReservationDatesValidator;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ReservationDto addReservation(@RequestBody ReservationEntity reservationEntity) {
        return reservationService.createReservation(reservationEntity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteReservationById(@PathVariable Long id){
        return reservationService.deleteReservationById(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReservationDto changeReservationDatesByReservationId(@PathVariable Long id, @RequestBody ReservationDatesValidator date){
    return reservationService.changeReservationDatesByReservationId(id, date.getDateStart(), date.getDateEnd());
}

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<ReservationDto> getAllReservations() { return reservationService.getAllReservations(); }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<ReservationDto> getAllReservationsByUser(@PathVariable Long id){
        return reservationService.getAllReservationsByUserId(id);
    }

}
