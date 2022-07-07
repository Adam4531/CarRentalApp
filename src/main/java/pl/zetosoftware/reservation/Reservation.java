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

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "car_id")
    private Long carId;

    @Column(name = "date_start", columnDefinition = "DATE")
    private LocalDateTime dateStart;

    @Column(name = "date_end", columnDefinition = "DATE")
    private LocalDateTime dateEnd;

    private BigDecimal cost;

    @Column(name = "payment_in_advance")
    private BigDecimal paymentInAdvance;

    @Enumerated(EnumType.STRING)
    private Status status;

}
