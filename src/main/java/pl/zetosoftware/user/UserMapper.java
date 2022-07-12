package pl.zetosoftware.user;

import org.springframework.stereotype.Component;
import pl.zetosoftware.user.dto.UserRequestDto;
import pl.zetosoftware.user.dto.UserResponseDto;
import pl.zetosoftware.user.value_objects.*;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserResponseDto fromUserEntityToUserResponseDto(UserEntity userEntity){
        return UserResponseDto.builder()
                .id(userEntity.getId())
                .firstName(userEntity.getFirstName().toString())
                .secondName(userEntity.getSecondName().toString())
                .phoneNumber(userEntity.getPhoneNumber().toString())
                .email(userEntity.getEmail().toString())
                .build();
    }

    public List<UserResponseDto> fromUserEntityListToUserResponseList(List<UserEntity> userEntityList){
        return userEntityList.stream()
                .map(this::fromUserEntityToUserResponseDto)
                .collect(Collectors.toList());
    }

    public UserEntity fromUserRequestDtoToUserEntity(UserRequestDto userRequestDto){
        return UserEntity.builder()
                .login(new Login(userRequestDto.login()))
                .firstName(new Name(userRequestDto.firstName()))
                .secondName(new Name(userRequestDto.secondName()))
                .email(new Email(userRequestDto.email()))
                .password(new Password(userRequestDto.password()))
                .phoneNumber(new PhoneNumber(userRequestDto.phoneNumber()))
                .pesel(new Pesel(userRequestDto.pesel()))
                .build();
    }

}
