package pl.zetosoftware.user;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import pl.zetosoftware.user.value_objects.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "first_name")
    public String firstName;

    @Column(name = "second_name")
    public String lastName;

    public String password;

    @Column(name = "phone_number")
    public String phoneNumber;

    public String email;

    public String pesel;

    public User(String firstName, String lastName, String password, String phoneNumber, String email, String pesel) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.pesel = pesel;
    }

    public void changeFirstName(String firstName){
        this.firstName = firstName;
    }

    public void changeLastName(String lastName){
        this.lastName = lastName;
    }

}