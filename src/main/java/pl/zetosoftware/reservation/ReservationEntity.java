package pl.zetosoftware.reservation;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.zetosoftware.car.CarEntity;
import pl.zetosoftware.reservation.value_objects.CostValidator;
import pl.zetosoftware.reservation.value_objects.PaymentInAdvanceValidator;
import pl.zetosoftware.reservation.value_objects.ReservationDatesValidator;
import pl.zetosoftware.user.UserEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "RESERVATIONS")
@NoArgsConstructor
@Getter
public class ReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

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
    private ReservationDatesValidator date;

    @Embedded
    private CostValidator cost;

    @Embedded
    private PaymentInAdvanceValidator paymentInAdvance;

    @Builder
    public ReservationEntity(Long id, UserEntity userId, CarEntity carId, ReservationDatesValidator date, CostValidator cost, PaymentInAdvanceValidator paymentInAdvance) {
        this.id = id;
        this.userId = userId;
        this.carId = carId;
        this.date = date;
        this.cost = cost;
        this.paymentInAdvance = paymentInAdvance;
    }

//    public void changeDateStart(ReservationDatesValidator dateStart) {
//        this.date.dateStart = dateStart;
//    }

    public LocalDateTime getStartDate(){
        return date.dateStart;
    }

    public LocalDateTime getEndDate(){
        return date.dateEnd;
    }

    public void changeDateEnd(LocalDateTime dateEnd) {
        this.date.dateEnd = dateEnd;
    }

    public void changeCost(BigDecimal cost) {
        this.cost = new CostValidator(cost);
    }

    public void changePaymentInAdvance(BigDecimal paymentInAdvance) {
        this.paymentInAdvance = new PaymentInAdvanceValidator(paymentInAdvance);
    }


}