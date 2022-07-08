package pl.zetosoftware.reservation;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.zetosoftware.reservation.enums.Status;
import pl.zetosoftware.reservation.value_objects.Cost;
import pl.zetosoftware.reservation.value_objects.PaymentInAdvance;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "RESERVATIONS")
@NoArgsConstructor
@Getter
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private Long userId;

    private Long carId;

    @Column(columnDefinition = "DATE")
    private LocalDateTime dateStart; //TODO maybe create its own class to format date if it does have any sense

    @Column(columnDefinition = "DATE")
    private LocalDateTime dateEnd; //TODO same as higher

    @Embedded
    private Cost cost;

    @Embedded
    private PaymentInAdvance paymentInAdvance;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Builder
    public Reservation(Long id, Long userId, Long carId, LocalDateTime dateStart, LocalDateTime dateEnd, Cost cost, PaymentInAdvance paymentInAdvance, Status status) {
        this.id = id;
        this.userId = userId;
        this.carId = carId;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.cost = cost;
        this.paymentInAdvance = paymentInAdvance;
        this.status = status;
    }

    public void changeStatus(Status status){
        this.status = status;
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



