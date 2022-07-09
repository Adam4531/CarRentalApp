package pl.zetosoftware.car.value_objects;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@NoArgsConstructor
@Embeddable
public class Brand implements Validator{

    private static final String BRAND_NAME_REGEX = "[a-zA-Z\\d]+";
    @Column
    private String brand;

    public Brand(String brand) {
        if(!containsValidCharacters(brand, BRAND_NAME_REGEX))
            throw new IllegalStateException("BRAND NAME MAY CONTAIN ONLY LETTERS OR NUMBERS !!");
        if(!isValidLength(brand, 1, 20))
            throw new IllegalStateException("BRAND NAME MUST BE BETWEEN 1 AND 20 CHARACTERS !!");
        this.brand = brand;
    }

    @Override
    public String toString() {
        return brand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Brand brand1 = (Brand) o;
        return brand.equals(brand1.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand);
    }
}
