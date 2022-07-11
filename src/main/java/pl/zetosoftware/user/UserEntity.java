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
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    @OneToMany(mappedBy = "user_id")
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
    public UserEntity(Login login, Name firstName, Name secondName, Password password, PhoneNumber phoneNumber, Email email, Pesel pesel) {
        this.login = login;
        this.firstName = firstName;
        this.secondName = secondName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.pesel = pesel;
    }

    public void changeLogin(String changedLogin){ this.login = new Login(changedLogin); }

    public void changeFirstName(String changedFirstName){ this.firstName = new Name(changedFirstName); }

    public void changeSecondName(String changedSecondName){ this.secondName = new Name(changedSecondName); }

    public void changePassword(String changedPassword){ this.password = new Password(changedPassword); }

    public void changePhoneNumber(String changedPhoneNumber){ this.phoneNumber = new PhoneNumber(changedPhoneNumber); }

    public void changeEmail(String changedEmail){ this.email = new Email(changedEmail); }

    public void changePesel(String changedPesel){ this.pesel = new Pesel(changedPesel); }

}