package pl.zetosoftware.reservation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cars")
public class ReservationController {

    private final ReservationService reservationService;
}
