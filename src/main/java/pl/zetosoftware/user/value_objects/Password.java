package pl.zetosoftware.user.value_objects;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
public class Password {

    private final String SPECIAL_CHARS_AND_ENGLISH_LETTERS = "\\x21-\\x7E";

    @Getter private String password;

    public Password(String password) {
        if ( Objects.isNull(password) ) {
            throw new IllegalStateException("Password cant be null!");
        }

        if ( isInValidLength(password) ) {
            throw new IllegalStateException("Password must be between 7 and 28 characters length!");
        }

        if ( !password.matches("[" + SPECIAL_CHARS_AND_ENGLISH_LETTERS + "]")){
            throw new IllegalStateException("Password must contain only special chars and english letters!");
        }

        this.password = password;
    }

    private boolean isInValidLength(String password){
        return password.length() < 7 || password.length() > 28;
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