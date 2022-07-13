package pl.zetosoftware.reservation.value_objects;

import lombok.NoArgsConstructor;
import pl.zetosoftware.interfaces.Validator;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;
import java.util.Objects;

@NoArgsConstructor
@Embeddable
public class ReservationDatesValidator implements Validator{


    @Column
    public LocalDateTime dateStart;

    @Column
    public LocalDateTime dateEnd;

    public ReservationDatesValidator(LocalDateTime dateStart, LocalDateTime dateEnd) {
        if(Objects.isNull(dateStart)){
            throw new IllegalArgumentException("Date start can't be null!!");
        }
        if(Objects.isNull(dateEnd)){
            throw new IllegalArgumentException("Date end can't be null!");
        }
        if(dateStart.isAfter(dateEnd)){
            throw new IllegalArgumentException("Date of start must be before the date of end reservation!");
        }
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationDatesValidator that = (ReservationDatesValidator) o;
        return Objects.equals(dateStart, that.dateStart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateStart);
    }


}
