package pl.zetosoftware.user.value_objects;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
public class Login {
    private static final String ENGLISH_LETTERS_NUMBERS_DOT_UNDERSCORE_DASH= "[a-zA-Z0-9._\\-]+";

    @Column(nullable = false)
    private String login;

    public Login(String login) {
        if (Objects.isNull(login)) {
            throw new IllegalStateException("Login cant be null!");
        }

        if ( !isValidLength(login) ) {
            throw new IllegalStateException("Login must be between 3 and 28 characters length!");
        }

        if ( !containValidCharacters(login) ) {
            throw new IllegalStateException("Login must contain only english letters, numbers, dots, underscores or dashes!");
        }

        this.login = login;
    }
    public boolean containValidCharacters(String login) {
        return login.matches(ENGLISH_LETTERS_NUMBERS_DOT_UNDERSCORE_DASH);
    }

    public boolean isValidLength(String login) {
        return login.length() > 3 && login.length() < 28;
    }

    @Override
    public String toString() {
        return login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Login login1 = (Login) o;
        return login.equals(login1.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }
}
