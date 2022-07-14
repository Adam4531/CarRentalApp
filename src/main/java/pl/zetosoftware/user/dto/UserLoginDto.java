package pl.zetosoftware.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserLoginDto {

    @Email
    private String email;
    private String password;
}