package pl.zetosoftware.car.value_objects;

import lombok.NoArgsConstructor;
import pl.zetosoftware.car.enums.BodyTypeEnum;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@NoArgsConstructor
@Embeddable
public class BodyTypeValidator {

    @Column
    private Enum<BodyTypeEnum> bodyType;

    public BodyTypeValidator(Enum<BodyTypeEnum> bodyType) {
        if(Objects.isNull(bodyType))
            throw new IllegalStateException("BODY TYPE CANNOT BE NULL !!");
        this.bodyType = bodyType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BodyTypeValidator bodyTypeValidator1 = (BodyTypeValidator) o;
        return Objects.equals(bodyType, bodyTypeValidator1.bodyType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bodyType);
    }

    public Enum<BodyTypeEnum> toEnum() { return bodyType; }
}
