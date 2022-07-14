package pl.zetosoftware.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.zetosoftware.user.dto.UserRequestDto;
import pl.zetosoftware.user.dto.UserResponseDto;
import pl.zetosoftware.user.exception.EmailAlreadyExist;
import pl.zetosoftware.user.exception.LoginAlreadyExist;
import pl.zetosoftware.user.value_objects.EmailValidator;
import pl.zetosoftware.user.value_objects.LoginValidator;

@Service
public class UserRegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserRegistrationService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    public UserResponseDto register(UserRequestDto userRequestDto){
        if ( existWithEmail(userRequestDto.email()) ) {
            throw new EmailAlreadyExist();
        }
        if ( existWithLogin(userRequestDto.login()) ) {
            throw new LoginAlreadyExist();
        }
        var userEntity = userMapper.fromUserRequestDtoToUserEntity(userRequestDto);
        encryptUserPassword(userEntity);
        userRepository.save(userEntity);
        return userMapper.fromUserEntityToUserResponseDto(userEntity);
    }

    public Boolean existWithEmail(String email) {
        return userRepository.existsUserEntityByEmail(new EmailValidator(email));
    }

    public Boolean existWithLogin(String login) {
        return userRepository.existsUserEntityByLogin(new LoginValidator(login));
    }

    private void encryptUserPassword(UserEntity userEntity) {
        userEntity.changePassword(passwordEncoder.encode(userEntity.getPassword().toString()));
    }

}
