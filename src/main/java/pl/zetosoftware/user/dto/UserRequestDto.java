package pl.zetosoftware.user.dto;

import lombok.Builder;
import lombok.NoArgsConstructor;


public record UserRequestDto(String login, String firstName, String secondName,
                             String password, String phoneNumber, String email,
                             String pesel) {

    @Builder public UserRequestDto {}

}
