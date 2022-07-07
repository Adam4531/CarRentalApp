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
    @Column(nullable = false)
    private Long id;
    @Embedded
    private Login login;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "first_name"))
    })
    private Name firstName;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "second_name"))
    })
    private Name secondName;
    @Embedded
    private Password password;
    @Embedded
    private PhoneNumber phoneNumber;
    @Embedded
    private Email email;
    @Embedded
    private Pesel pesel;
    @Builder
    public User(Login login, Name firstName, Name secondName, Password password, PhoneNumber phoneNumber, Email email, Pesel pesel) {
        this.login = login;
        this.firstName = firstName;
        this.secondName = secondName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.pesel = pesel;
    }

    public void changeFirstName(String firstName){
        this.firstName = new Name(firstName);
    }

    public void changeLastName(String lastName){
        this.secondName = new Name(lastName);
    }

    public void changePassword(String password){
        this.password = new Password(password);
    }

}