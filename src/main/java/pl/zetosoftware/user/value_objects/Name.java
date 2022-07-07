package pl.zetosoftware.user.value_objects;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
public class Name {

    private static final String POLISH_SIGNS = "\\p{IsAlphabetic}";

    @Column
    private String name;

    public Name(String name) {
        if( Objects.isNull(name) ) {
            throw new IllegalStateException("Name can't be null!");
        }

        if ( !containValidCharacters(name) ) {
            throw new IllegalStateException("Name must contain only letters!");
        }

        if ( !isValidLength(name) ) {
            throw new IllegalStateException("Name must be between 3 and 50 characters long!");
        }

        name = toLowerCaseThenCapitalize(name);
        this.name = name;
    }

    public boolean containValidCharacters(String name) {
        return name.matches("[a-zA-Z- " + POLISH_SIGNS + "]+");
    }

    public boolean isValidLength(String name) {
        return name.length() > 3 && name.length() < 50;
    }

    public String toLowerCaseThenCapitalize(String string){
        return string.substring(0,1).toUpperCase() + string.substring(1).toLowerCase();
    }

    @Override
    public String toString(){
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name1 = (Name) o;
        return name.equals(name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
