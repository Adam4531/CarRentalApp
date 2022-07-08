package pl.zetosoftware.reservation;

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

}



