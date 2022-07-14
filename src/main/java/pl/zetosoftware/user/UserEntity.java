package pl.zetosoftware.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.zetosoftware.security.Role;
import pl.zetosoftware.user.value_objects.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Embedded
    private LoginValidator login;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "first_name"))
    })
    private NameValidator firstName;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "second_name"))
    })
    private NameValidator secondName;
    @Embedded
    private PasswordValidator password;
    @Embedded
    private PhoneNumberValidator phoneNumber;
    @Embedded
    private EmailValidator email;
    @Embedded
    private PeselValidator pesel;

    @ManyToMany(fetch = FetchType.EAGER)
    @Setter
    private Set<Role> roles;

    @Builder
    public UserEntity(LoginValidator login, NameValidator firstName, NameValidator secondName, PasswordValidator password, PhoneNumberValidator phoneNumber, EmailValidator email, PeselValidator pesel) {
        this.login = login;
        this.firstName = firstName;
        this.secondName = secondName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.pesel = pesel;
    }

    public void changeLogin(String changedLogin){ this.login = new LoginValidator(changedLogin); }

    public void changeFirstName(String changedFirstName){ this.firstName = new NameValidator(changedFirstName); }

    public void changeSecondName(String changedSecondName){ this.secondName = new NameValidator(changedSecondName); }

    public void changePassword(String changedPassword){ this.password = new PasswordValidator(changedPassword); }

    public void changePhoneNumber(String changedPhoneNumber){ this.phoneNumber = new PhoneNumberValidator(changedPhoneNumber); }

    public void changeEmail(String changedEmail){ this.email = new EmailValidator(changedEmail); }

    public void changePesel(String changedPesel){ this.pesel = new PeselValidator(changedPesel); }

}