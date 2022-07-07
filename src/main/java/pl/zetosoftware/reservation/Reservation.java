package pl.zetosoftware.reservation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.zetosoftware.reservation.enums.Status;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
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
    private Integer carId;

    @Column(name = "date_start")
    private LocalDateTime dateStart;

    @Column(name = "date_end")
    private LocalDateTime dateEnd;
    private BigDecimal cost;

    @Column(name = "payment_in_advance")
    private BigDecimal paymentInAdvance;

    private Status status;

}
