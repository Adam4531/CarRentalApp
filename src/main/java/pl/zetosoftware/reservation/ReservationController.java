package pl.zetosoftware.reservation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.zetosoftware.reservation.dto.ReservationDto;
import pl.zetosoftware.reservation.value_objects.ReservationDatesValidator;
import pl.zetosoftware.user.dto.UserResponseDto;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ReservationDto addReservation(@RequestBody ReservationEntity reservationEntity) {
        return reservationService.createReservation(reservationEntity);
    }

    @PostMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public String deleteReservationById(@RequestBody Long Id){
        return reservationService.deleteReservationById(Id);
    }

//    @PatchMapping("/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public ReservationDto changeReservationDatesByReservationId(@PathVariable Long id, @RequestBody LocalDate dateStart, LocalDate dateEnd){
//        return reservationService.changeReservationDates(id, dateStart, dateEnd);
//    }
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReservationDto changeReservationDatesByReservationId(@PathVariable Long id, @RequestBody ReservationDatesValidator date){
    return reservationService.changeReservationDates(id, date.getDateStart(), date.getDateEnd());
}

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<ReservationDto> getAllReservations() { return reservationService.getAllReservations(); }

//    @GetMapping("/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public List<ReservationDto> getAllReservationsByUser(@PathVariable Long id){
//        return reservationService.getAllReservationsById(id);
//    }

//    @GetMapping("/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public List<ReservationDto> getAllReservationsByUser(@RequestBody Long id){
//        return reservationService.getAllReservationsById(id);
//    }



}
