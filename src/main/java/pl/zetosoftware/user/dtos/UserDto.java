package pl.zetosoftware.user.dtos;

import lombok.Builder;

public record UserDto(String firstName, String secondName, String phoneNumber, String email) {

    @Builder public UserDto {}

}
