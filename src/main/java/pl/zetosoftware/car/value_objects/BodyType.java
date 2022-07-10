package pl.zetosoftware.car.value_objects;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@NoArgsConstructor
@Embeddable
public class BodyType {

    @Column
    private Enum<pl.zetosoftware.car.enums.BodyType> bodyType;

    public BodyType(Enum<pl.zetosoftware.car.enums.BodyType> bodyType) {
        if(Objects.isNull(bodyType))
            throw new IllegalStateException("BODY TYPE CANNOT BE NULL !!");
        this.bodyType = bodyType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BodyType bodyType1 = (BodyType) o;
        return Objects.equals(bodyType, bodyType1.bodyType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bodyType);
    }

    public Enum<pl.zetosoftware.car.enums.BodyType> toEnum() { return bodyType; }
}
