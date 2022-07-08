package pl.zetosoftware.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.zetosoftware.user.value_objects.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "USERS")
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

    public void changeLogin(Login changedLogin){ this.login = changedLogin; }

    public void changeFirstName(Name changedFirstName){ this.firstName = changedFirstName; }

    public void changeSecondName(Name changedSecondName){ this.secondName = changedSecondName; }

    public void changePassword(Password changedPassword){ this.password = changedPassword; }

    public void changePhoneNumber(PhoneNumber changedPhoneNumber){ this.phoneNumber = changedPhoneNumber; }

    public void changeEmail(Email changedEmail){ this.email = changedEmail; }

    public void changePesel(Pesel changedPesel){ this.pesel = changedPesel; }

}