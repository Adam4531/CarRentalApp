package pl.zetosoftware.reservation;

import lombok.RequiredArgsConstructor;
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
    public ReservationEntity addReservation(@RequestBody ReservationEntity reservationEntity) {
        return reservationService.createReservation(reservationEntity);
    }


    @PostMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public String deleteReservationById(@RequestBody Long Id){
        return reservationService.deleteReservationById(Id);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<ReservationDto> getAllReservations() { return reservationService.getAllReservations(); }

//    @GetMapping("/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public List<ReservationDto> getAllReservationsByUser(@RequestBody Long id){
//        return reservationService.getAllReservationsById(id);
//    }

//    @GetMapping("/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public List<ReservationDto> getA

}
