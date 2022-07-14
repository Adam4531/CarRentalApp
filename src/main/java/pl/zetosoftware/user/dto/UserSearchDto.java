package pl.zetosoftware.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserSearchDto {

    private Long id;
    private String firstName;
    private String secondName;
    private String email;
    private Boolean activated;
}
