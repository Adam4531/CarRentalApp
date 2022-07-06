package pl.zetosoftware.user.value_objects;

import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
public class Pesel {

    private static final String DIGITS = "[0-9]+";

    private String pesel;

    public Pesel(String pesel) {
        if ( Objects.isNull(pesel) ) {
            throw new IllegalArgumentException("Pesel can't be null!");
        }
        if ( !isValidLength(pesel) ){
            throw new IllegalStateException("Pesel must be 11 digits length!");
        }
        if ( !containsOnlyDigits(pesel) ){
            throw new IllegalArgumentException("Pesel must contain only digits!");
        }
        this.pesel = pesel;
    }

    public boolean isValidLength(String pesel){
        return pesel.length() == 11;
    }

    public boolean containsOnlyDigits(String pesel){
        return pesel.matches(DIGITS);
    }

    @Override
    public String toString() {
        return pesel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pesel pesel1 = (Pesel) o;
        return pesel.equals(pesel1.pesel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pesel);
    }
}
