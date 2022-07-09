package pl.zetosoftware.car.value_objects;

import lombok.NoArgsConstructor;
import pl.zetosoftware.car.enums.BodyTypeEnum;

import javax.persistence.Embeddable;
import java.util.Objects;

@NoArgsConstructor
@Embeddable
public class BodyType {

    private Enum<BodyTypeEnum> bodyType;

    public BodyType(Enum<BodyTypeEnum> bodyType) {
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
}
