package pl.zetosoftware.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.zetosoftware.global.dto.ErrorsListDto;
import pl.zetosoftware.reservation.dto.ReservationRequestDto;


@RestController
@RequestMapping("/api")
public class ReservationWebController {
    @Autowired
    private ReservationCreateService reservationCreateService;

    @PostMapping("/reservation")
    public ErrorsListDto create(@RequestBody ReservationRequestDto reservation) {
        return reservationCreateService.create(reservation);
    }
}
