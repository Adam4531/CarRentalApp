package pl.zetosoftware.reservation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.zetosoftware.reservation.enums.Status;

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
    private LocalDateTime dateStart;

    @Column(columnDefinition = "DATE")
    private LocalDateTime dateEnd;

    private BigDecimal cost;

    private BigDecimal paymentInAdvance;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Reservation(Long id, Long userId, Long carId, LocalDateTime dateStart, LocalDateTime dateEnd, BigDecimal cost, BigDecimal paymentInAdvance, Status status) {
        this.id = id;
        this.userId = userId;
        this.carId = carId;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.cost = cost;
        this.paymentInAdvance = paymentInAdvance;
        this.status = status;
    }
}
