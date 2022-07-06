package pl.zetosoftware.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.zetosoftware.user.value_objects.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private FirstName firstName;
    @Embedded
    private SecondName secondName;
    @Embedded
    private Password password;
    @Embedded
    private PhoneNumber phoneNumber;
    @Embedded
    private Email email;
    @Embedded
    private Pesel pesel;

    public User(FirstName firstName, SecondName secondName, Password password, PhoneNumber phoneNumber, Email email, Pesel pesel) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.pesel = pesel;
    }

}