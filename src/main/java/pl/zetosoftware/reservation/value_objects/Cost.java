package pl.zetosoftware.reservation.value_objects;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;
import java.util.Objects;

@NoArgsConstructor
@Embeddable
public class Cost {

    @Column
    private BigDecimal cost;

    public Cost(BigDecimal cost) {
        if (Objects.isNull(cost)) {
            throw new IllegalArgumentException("Cost can't be null!");
        }
        if (isGreaterThanLimit(cost)) {
            throw new IllegalStateException("Cost must be lower than 7 digits");
        }
        this.cost = cost;
    }

    public boolean isGreaterThanLimit(BigDecimal cost) {
        BigDecimal limit = BigDecimal.valueOf(10000000);
        if (limit.compareTo(cost) > 0) {
            return false;
        }
        return true;


    }

    @Override
    public String toString() {
        return cost.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cost cost1 = (Cost) o;
        return cost.equals(cost1.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cost);
    }
}
