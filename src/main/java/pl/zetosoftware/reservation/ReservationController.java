package pl.zetosoftware.reservation;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.zetosoftware.reservation.dto.ReservationDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation addReservation(@RequestBody Reservation reservation) {
        return reservationService.createReservation(reservation);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<ReservationDto> getAllReservations() { return reservationService.getAllReservations(); }

}
