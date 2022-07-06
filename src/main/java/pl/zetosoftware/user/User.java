package pl.zetosoftware.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.zetosoftware.user.value_objects.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private Name firstName;
    @Embedded
    private Name lastName;
    @Embedded
    private Password password;
    @Embedded
    private PhoneNumber phoneNumber;
    @Embedded
    private Email email;
    @Embedded
    private Pesel pesel;
    @Builder
    public User(Name firstName, Name lastName, Password password, PhoneNumber phoneNumber, Email email, Pesel pesel) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.pesel = pesel;
    }

    public void changeFirstName(String firstName){
        this.firstName = new Name(firstName);
    }

    public void changeLastName(String lastName){
        this.lastName = new Name(lastName);
    }

    public void changePassword(String password){
        this.password = new Password(password);
    }

}