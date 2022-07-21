package pl.zetosoftware.reservation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.zetosoftware.car.CarFieldsService;
import pl.zetosoftware.car.CarService;
import pl.zetosoftware.reservation.dto.ReservationRequestDto;
import pl.zetosoftware.reservation.value_objects.CostValidator;
import pl.zetosoftware.reservation.value_objects.PaymentInAdvanceValidator;
import pl.zetosoftware.reservation.value_objects.ReservationDatesValidator;
import pl.zetosoftware.user.UserRepository;
import pl.zetosoftware.user.value_objects.EmailValidator;

import java.math.BigDecimal;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

@Component
@RequiredArgsConstructor
public class ReservationWebMapper {
    private final CarService carService;

    private final UserRepository userRepository;

    private final CarFieldsService carFieldsService;

    public ReservationEntity fromReservationRequestDtoToReservationEntity(ReservationRequestDto reservationRequestDto){
        return ReservationEntity.builder()
                //.userId(userService.getUser(reservationRequestDto.userId()))
                //OPCJONALNA ZMIANA NA userId ZAMIAST EMAILU
                .userId(userRepository.findUserByEmail(new EmailValidator(reservationRequestDto.email())))
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

        return carFieldsService.setInitialPrice(reservationRequestDto.carId()).multiply(daysBetween);
    }

    public BigDecimal setPaymentInAdvance(Long carId){
        var car = carService.getCarEntityById(carId);
        var paymentInAdvance = BigDecimal.valueOf(car.getNewCarCost().toLong()).
                multiply(BigDecimal.valueOf(0.01));
        if (paymentInAdvance.compareTo(BigDecimal.valueOf(1000)) < 1) return BigDecimal.valueOf(1000.00);
        return paymentInAdvance;
    }

}
