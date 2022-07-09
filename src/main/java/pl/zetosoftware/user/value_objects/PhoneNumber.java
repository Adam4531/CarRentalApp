package pl.zetosoftware.user.value_objects;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
public class PhoneNumber implements Validator {

    private static final String DIGITS = "[0-9]+";

    @Column
    private String phoneNumber;

    public PhoneNumber(String phoneNumber){
        if ( Objects.isNull(phoneNumber) ){
            throw new IllegalArgumentException("Phone number can't be null!");
        }

        if ( !isValidLength(phoneNumber, 9, 9) ){
            throw new IllegalArgumentException("Phone number must be 9 digits length!");
        }

        if ( !containsValidCharacters(phoneNumber, DIGITS) ){
            throw new IllegalArgumentException("Phone number may contain only digits!");
        }

        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber that = (PhoneNumber) o;
        return phoneNumber.equals(that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber);
    }

}
