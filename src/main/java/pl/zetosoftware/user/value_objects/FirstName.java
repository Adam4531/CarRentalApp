package pl.zetosoftware.user.value_objects;

import javax.persistence.Embeddable;

@Embeddable
public class FirstName {

    String firstName;

    public FirstName(String firstName) {
        if ( containCharactersOtherThanLetters(firstName) ) {
            throw new IllegalStateException("Name must contain only letters!");
        }
        if ( isTooShort(firstName) ) {
            throw new IllegalStateException("Name must be at least 3 characters long!");
        }

        this.firstName = firstName;

    }

    private boolean isTooShort(String firstName) {
        return firstName.length() < 3;
    }

    private boolean containCharactersOtherThanLetters(String firstName) {
        return !firstName.matches("[a-zA-Z]+");
    }

}
