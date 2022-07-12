package pl.zetosoftware.car.value_objects;

import lombok.NoArgsConstructor;
import pl.zetosoftware.car.enums.TypeOfFuelEnum;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@NoArgsConstructor
@Embeddable
public class TypeOfFuelValidator {

    @Column
    private Enum<TypeOfFuelEnum> typeOfFuel;

    public TypeOfFuelValidator(Enum<TypeOfFuelEnum> typeOfFuel) {
        if(Objects.isNull(typeOfFuel))
            throw new IllegalStateException("TYPE OF FUEL CANNOT BE NULL !!");
        this.typeOfFuel = typeOfFuel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeOfFuelValidator that = (TypeOfFuelValidator) o;
        return typeOfFuel.equals(that.typeOfFuel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeOfFuel);
    }

    public Enum<TypeOfFuelEnum> toEnum() { return typeOfFuel; }

}
