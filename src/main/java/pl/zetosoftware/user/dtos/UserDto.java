package pl.zetosoftware.user.dtos;

import lombok.Builder;
import pl.zetosoftware.user.value_objects.*;

public record UserDto(String firstName, String secondName, String phoneNumber, String email) {

    @Builder public UserDto {}

}
