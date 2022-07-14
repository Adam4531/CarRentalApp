package pl.zetosoftware.reservation;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.zetosoftware.basic.BasicEntity;
import pl.zetosoftware.car.CarEntity;
import pl.zetosoftware.reservation.value_objects.CostValidator;
import pl.zetosoftware.reservation.value_objects.PaymentInAdvanceValidator;
import pl.zetosoftware.user.UserEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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

    @Column(columnDefinition = "DATE")      //TODO CHANGE to ReservationsDate class and use one field, add with Embeddable
    private LocalDateTime dateStart;        // to check if dateEnd is not before dateStart

    @Column(columnDefinition = "DATE")
    private LocalDateTime dateEnd;

    @Embedded
    private CostValidator cost;

    @Embedded
    private PaymentInAdvanceValidator paymentInAdvance;

    @Builder
    public ReservationEntity(UserEntity userId, CarEntity carId, LocalDateTime dateStart, LocalDateTime dateEnd, CostValidator cost, PaymentInAdvanceValidator paymentInAdvance) {
        this.userId = userId;
        this.carId = carId;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.cost = cost;
        this.paymentInAdvance = paymentInAdvance;
    }

    public void changeDateStart(LocalDateTime dateStart){
        this.dateStart = dateStart;
    }

    public void changeDateEnd(LocalDateTime dateEnd){
        this.dateEnd = dateEnd;
    }

    public void changeCost(BigDecimal cost){
        this.cost = new CostValidator(cost);
    }

    public void changePaymentInAdvance(BigDecimal paymentInAdvane){
        this.paymentInAdvance = new PaymentInAdvanceValidator(paymentInAdvane);
    }
}