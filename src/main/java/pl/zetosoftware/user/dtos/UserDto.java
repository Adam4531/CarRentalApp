package pl.zetosoftware.user.dtos;

import lombok.Builder;
import pl.zetosoftware.annotations.GenerateTypescript;

@GenerateTypescript

public record UserDto(Long id, String login, String firstName, String secondName, String phoneNumber, String email) {

    @Builder public UserDto {}

}
