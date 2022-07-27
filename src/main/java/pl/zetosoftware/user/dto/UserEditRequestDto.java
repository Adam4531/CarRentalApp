package pl.zetosoftware.user.dto;

import lombok.Builder;
import lombok.NoArgsConstructor;


public record UserEditRequestDto(String login, String firstName, String secondName, String phoneNumber, String email,
                             String pesel) {

    @Builder public UserEditRequestDto {}

}
