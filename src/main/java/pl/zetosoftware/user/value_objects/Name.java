package pl.zetosoftware.user.value_objects;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
public class Name implements Validator{
    private static final String POLISH_ALPHABET = "[a-zA-Z-\\p{IsAlphabetic}]+";

    @Column
    private String name;

    public Name(String name) {
        if( Objects.isNull(name) )
            throw new IllegalStateException("Name can't be null!");
        if ( !isValidLength(name, 3, 60) )
            throw new IllegalStateException("Name must be between 3 and 60 characters long!");
        if ( !containsValidCharacters(name, POLISH_ALPHABET) )
            throw new IllegalStateException("Name may contain only letters!");
        name = toLowerCaseThenCapitalize(name);
        this.name = name;
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
