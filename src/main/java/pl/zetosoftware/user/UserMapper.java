package pl.zetosoftware.user;

import org.springframework.stereotype.Component;
import pl.zetosoftware.user.dtos.UserDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserDto fromUserToUserDTO(User user){
        return UserDto.builder()
                .firstName(user.getFirstName().toString())
                .secondName(user.getSecondName().toString())
                .phoneNumber(user.getPhoneNumber().toString())
                .email(user.getEmail().toString())
                .build();
    }

    public List<UserDto> fromUserListToUserDtoList(List<User> userList){
        return userList.stream()
                .map(this::fromUserToUserDTO)
                .collect(Collectors.toList());
    }

}
