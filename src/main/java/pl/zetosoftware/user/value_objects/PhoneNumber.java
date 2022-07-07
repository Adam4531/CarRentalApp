package pl.zetosoftware.user.value_objects;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
public class PhoneNumber {

    private static final String DIGITS = "[0-9]+";

    @Column
    private String phoneNumber;

    public PhoneNumber(String phoneNumber){
        if ( Objects.isNull(phoneNumber) ){
            throw new IllegalArgumentException("Phone number can't be null!");
        }

        if ( !containOnlyDigits(phoneNumber) ){
            throw new IllegalArgumentException("Phone number must contain only digits!");
        }

        if ( !isValidLength(phoneNumber) ){
            throw new IllegalArgumentException("Phone number must be 9 digits length!");
        }

        this.phoneNumber = phoneNumber;
    }

    public boolean containOnlyDigits(String phoneNumber){
        return phoneNumber.matches(DIGITS);
    }

    public boolean isValidLength(String phoneNumber){
        return phoneNumber.length() == 9;
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
