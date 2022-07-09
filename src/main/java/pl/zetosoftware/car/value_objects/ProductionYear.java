package pl.zetosoftware.car.value_objects;

import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.util.Calendar;
import java.util.Objects;

@NoArgsConstructor
@Embeddable
public class ProductionYear {


    private Integer productionYear;
    int actualYear = Calendar.getInstance().get(Calendar.YEAR);

    public ProductionYear(Integer productionYear) {
        if(Objects.isNull(productionYear))
            throw new IllegalStateException("PRODUCTION YEAR CANNOT BE NULL !!");
        if(!isYearValid(productionYear))
            throw new IllegalStateException("PRODUCTION YEAR MUST BE AT LEAST 1884 !!");
        this.productionYear = productionYear;
    }

    public boolean isYearValid(Integer productionYear){
        return productionYear >= 1884 && productionYear <= actualYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductionYear that = (ProductionYear) o;
        return Objects.equals(productionYear, that.productionYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productionYear);
    }
}
