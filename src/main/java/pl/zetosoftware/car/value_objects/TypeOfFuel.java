package pl.zetosoftware.car.value_objects;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@NoArgsConstructor
@Embeddable
public class TypeOfFuel {

    @Column
    private Enum<pl.zetosoftware.car.enums.TypeOfFuel> typeOfFuel;

    public TypeOfFuel(Enum<pl.zetosoftware.car.enums.TypeOfFuel> typeOfFuel) {
        if(Objects.isNull(typeOfFuel))
            throw new IllegalStateException("TYPE OF FUEL CANNOT BE NULL !!");
        this.typeOfFuel = typeOfFuel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeOfFuel that = (TypeOfFuel) o;
        return typeOfFuel.equals(that.typeOfFuel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeOfFuel);
    }

    public Enum<pl.zetosoftware.car.enums.TypeOfFuel> toEnum() { return typeOfFuel; }

}
