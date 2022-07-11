package pl.zetosoftware.user;

import org.springframework.stereotype.Component;
import pl.zetosoftware.user.dto.UserDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserDto fromUserToUserDTO(UserEntity userEntity){
        return UserDto.builder()
                .id(userEntity.getId())
                .firstName(userEntity.getFirstName().toString())
                .secondName(userEntity.getSecondName().toString())
                .phoneNumber(userEntity.getPhoneNumber().toString())
                .email(userEntity.getEmail().toString())
                .build();
    }

    public List<UserDto> fromUserListToUserDtoList(List<UserEntity> userEntityList){
        return userEntityList.stream()
                .map(this::fromUserToUserDTO)
                .collect(Collectors.toList());
    }

}
