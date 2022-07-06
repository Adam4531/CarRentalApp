package pl.zetosoftware.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private String firstName;
    private String secondName;
    private String password;
    private String phoneNumber;
    private String email;
    private String pesel;

    public User(Long id, String firstName, String secondName, String password, String phoneNumber, String email, String pesel) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.pesel = pesel;
    }
}
