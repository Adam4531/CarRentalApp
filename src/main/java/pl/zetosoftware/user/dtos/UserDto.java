package pl.zetosoftware.user.dtos;

import lombok.Builder;
import pl.zetosoftware.annotations.GenerateTypescript;

@GenerateTypescript
// those fields should be: FirstName, SecondName, PhoneNumber, Email classes?
public record UserDto(Long id, String firstName, String secondName, String phoneNumber, String email) {

    @Builder public UserDto {}

}
