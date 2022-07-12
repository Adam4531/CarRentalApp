package pl.zetosoftware.car.value_objects;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@NoArgsConstructor
@Embeddable
public class ProductionYearValidator {


    @Column
    private Integer productionYear;
//    int actualYear = Calendar.getInstance().get(Calendar.YEAR);

    public ProductionYearValidator(Integer productionYear) {
        if(Objects.isNull(productionYear))
            throw new IllegalStateException("PRODUCTION YEAR CANNOT BE NULL !!");
        if(!isYearValid(productionYear))
            throw new IllegalStateException("PRODUCTION YEAR MUST BE AT LEAST 1884 !!");
        this.productionYear = productionYear;
    }

    public boolean isYearValid(Integer productionYear){
        return productionYear >= 1884 && productionYear <= 2022; // change to actualYear
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductionYearValidator that = (ProductionYearValidator) o;
        return Objects.equals(productionYear, that.productionYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productionYear);
    }

    public Integer toInteger(){
        return productionYear;
    }
}
