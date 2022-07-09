package pl.zetosoftware.car.value_objects;

import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.math.BigDecimal;
import java.util.Objects;

@NoArgsConstructor
@Embeddable
public class NewCarCost {

    private Long newCarCost;

    public NewCarCost(Long newCarCost) {
        if(Objects.isNull(newCarCost))
            throw new IllegalStateException("CAR COST CANNOT BE NULL !!");
        if(!isCostValid(newCarCost))
            throw new IllegalStateException("CAR COST MUST BE BETWEEN 0 AND 500 MILION !!");
        this.newCarCost = newCarCost;
    }

    public boolean isCostValid(Long newCarCost){
        return newCarCost >= 0 && newCarCost <= 500000000;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewCarCost that = (NewCarCost) o;
        return newCarCost.equals(that.newCarCost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(newCarCost);
    }

    public Long toLong(){
        return newCarCost;
    }
}
