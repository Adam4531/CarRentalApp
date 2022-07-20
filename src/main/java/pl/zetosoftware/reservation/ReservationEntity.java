package pl.zetosoftware.reservation;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.zetosoftware.global.BasicEntity;
import pl.zetosoftware.car.CarEntity;
import pl.zetosoftware.reservation.value_objects.CostValidator;
import pl.zetosoftware.reservation.value_objects.PaymentInAdvanceValidator;
import pl.zetosoftware.reservation.value_objects.ReservationDatesValidator;
import pl.zetosoftware.user.UserEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "RESERVATIONS")
@NoArgsConstructor
@Getter
public class ReservationEntity extends BasicEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private UserEntity userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", nullable = false)
    @JsonBackReference
    private CarEntity carId;

    @Column(columnDefinition = "DATE")
    @AttributeOverrides({
            @AttributeOverride(name = "dateStart", column = @Column(name = "date_start")),
            @AttributeOverride(name = "dateEnd", column = @Column(name = "date_end"))
    })
    @JsonBackReference
    private ReservationDatesValidator date;

    @Embedded
    private CostValidator cost;

    @Embedded
    private PaymentInAdvanceValidator paymentInAdvance;

    @Builder
    public ReservationEntity(UserEntity userId, CarEntity carId, ReservationDatesValidator date, CostValidator cost, PaymentInAdvanceValidator paymentInAdvance) {
        this.userId = userId;
        this.carId = carId;
        this.date = date;
        this.cost = cost;
        this.paymentInAdvance = paymentInAdvance;
    }

    public void changeReservationDates(LocalDate dateStart,LocalDate dateEnd) {
        this.date = new ReservationDatesValidator(dateStart, dateEnd);
    }

    public void changeCost(BigDecimal cost) {
        this.cost = new CostValidator(cost);
    }

    public void changePaymentInAdvance(BigDecimal paymentInAdvance) {
        this.paymentInAdvance = new PaymentInAdvanceValidator(paymentInAdvance);
    }


}