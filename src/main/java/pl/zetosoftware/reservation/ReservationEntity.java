package pl.zetosoftware.reservation;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.zetosoftware.car.CarEntity;
import pl.zetosoftware.car.enums.Status;
import pl.zetosoftware.reservation.value_objects.Cost;
import pl.zetosoftware.reservation.value_objects.PaymentInAdvance;
import pl.zetosoftware.user.UserEntity;

import javax.persistence.*;
import java.math.BigDecimal;
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

    @Column(columnDefinition = "DATE")      //TODO CHANGE to ReservationsDate class and use one field, add with Embeddable
    private LocalDateTime dateStart;        // to check if dateEnd is not before dateStart

    @Column(columnDefinition = "DATE")
    private LocalDateTime dateEnd;

    @Embedded
    private Cost cost;

    @Embedded
    private PaymentInAdvance paymentInAdvance;

    @Builder
    public ReservationEntity(Long id, UserEntity userId, CarEntity carId, LocalDateTime dateStart, LocalDateTime dateEnd, Cost cost, PaymentInAdvance paymentInAdvance) {
        this.id = id;
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
        this.cost = new Cost(cost);
    }

    public void changePaymentInAdvance(BigDecimal paymentInAdvane){
        this.paymentInAdvance = new PaymentInAdvance(paymentInAdvane);
    }
}