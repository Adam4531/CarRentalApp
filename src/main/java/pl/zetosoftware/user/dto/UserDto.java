package pl.zetosoftware.user.dto;

import lombok.Builder;

public record UserDto(Long id, String firstName, String secondName, String phoneNumber, String email) {

    @Builder public UserDto {}

}
