package pl.zetosoftware.user.value_objects;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
public class Password implements Validator{

    private static final String ENGLISH_LETTERS_NUMBERS_SPECIAL_CHARACTERS = "[\\x21-\\x7E]+";

    @Column
    private String password;

    public Password(String password) {
        if ( Objects.isNull(password) ) {
            throw new IllegalStateException("Password cant be null!");
        }

        if ( !isValidLength(password, 7, 28) ) {
            throw new IllegalStateException("Password must be between 7 and 28 characters length!");
        }

        if ( !containsValidCharacters(password, ENGLISH_LETTERS_NUMBERS_SPECIAL_CHARACTERS) ) {
            throw new IllegalStateException("Password may contain only english letters, numbers and special characters!");
        }

        this.password = password;
    }

    @Override
    public String toString() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Password password1 = (Password) o;
        return password.equals(password1.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(password);
    }

}