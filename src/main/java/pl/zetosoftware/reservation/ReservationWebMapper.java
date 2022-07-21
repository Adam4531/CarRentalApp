package pl.zetosoftware.reservation;

import org.springframework.stereotype.Component;
import pl.zetosoftware.car.CarService;
import pl.zetosoftware.reservation.dto.ReservationRequestDto;
import pl.zetosoftware.reservation.value_objects.CostValidator;
import pl.zetosoftware.reservation.value_objects.PaymentInAdvanceValidator;
import pl.zetosoftware.reservation.value_objects.ReservationDatesValidator;
import pl.zetosoftware.user.UserService;

import java.math.BigDecimal;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

@Component
public class ReservationWebMapper {
    private final UserService userService;

    private final CarService carService;

    public ReservationWebMapper(UserService userService, CarService carService) {
        this.userService = userService;
        this.carService = carService;
    }
    public ReservationEntity fromReservationRequestDtoToReservationEntity(ReservationRequestDto reservationRequestDto){
        return ReservationEntity.builder()
                .userId(userService.getUser(reservationRequestDto.userId()))
                .carId(carService.getCarEntityById(reservationRequestDto.carId()))
                .date(new ReservationDatesValidator(reservationRequestDto.dateStart(), reservationRequestDto.dateEnd()))
                .cost(new CostValidator(setTotalCost(reservationRequestDto)))
                .paymentInAdvance(new PaymentInAdvanceValidator(setPaymentInAdvance(reservationRequestDto.carId())))
                .build();
    }


    public BigDecimal setTotalCost(ReservationRequestDto reservationRequestDto){
        LocalDate dateStart = reservationRequestDto.dateStart();
        LocalDate dateEnd = reservationRequestDto.dateEnd();
        BigDecimal daysBetween = BigDecimal.valueOf(DAYS.between(dateStart, dateEnd));

        return carService.setInitialPrice(reservationRequestDto.carId()).multiply(daysBetween);
    }

    public BigDecimal setPaymentInAdvance(Long carId){
        BigDecimal paymentInAdvance = BigDecimal.valueOf(carService.findCarById(carId).newCarCost()).
                multiply(BigDecimal.valueOf(0.01));
        if (paymentInAdvance.compareTo(BigDecimal.valueOf(1000)) < 1) return BigDecimal.valueOf(1000.00);
        return paymentInAdvance;
    }
}
