package pl.zetosoftware.user.value_objects;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
public class Email {

//    private static final String DIGITS = "\\x30-\\x39";
//    private static final String LOWERCASE_LETTERS = "\\x61-\\x7A";
//    private static final String ATSIGN = "\\x40";
//    private static final String DOT = "\\x2E";
//    private static final String EMAIL_PATTERN_FROM_EMAILREGEXCOM = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+" +
//            "(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]" +
//            "|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)" +
//            "+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}" +
//            "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\" +
//            "x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    // te zmienne statyczne sa tu dodane tylko do czasu az sprawdzimy czy poniższy pattern sie sprawdza
    private static final String EMAIL_PATERN_FROM_BAELDUNG = "[^(.+)@(\\S+)$]";

    @Column(nullable = false)
    private String email;

    public Email(String email) {
        if ( Objects.isNull(email) ) {
            throw new IllegalArgumentException("Email can't be null!");
        }

        if ( !isValidLength(email) ) {
            throw new IllegalArgumentException("Email must be between 8 to 50 characters length!");
        }

        if ( !containValidCharacters(email) ) {
            throw new IllegalArgumentException("Email must contain only letters, digits, and '@' '.' signs!");
        }

        this.email = email;
    }

    public boolean containsAtSign(String email){
        return email.contains("@");
    }

    public boolean isValidLength(String email){
        return email.length() > 8 && email.length() < 50;
    }

    public boolean containValidCharacters(String email){
        email = email.toLowerCase();
        return email.matches(EMAIL_PATERN_FROM_BAELDUNG);
    }

    @Override
    public String toString() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email1 = (Email) o;
        return email.equals(email1.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

}
