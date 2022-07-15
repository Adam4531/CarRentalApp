package pl.zetosoftware.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.zetosoftware.user.dto.ErrorDto;
import pl.zetosoftware.user.dto.UserRegisterValidDto;
import pl.zetosoftware.user.dto.UserRequestDto;
import pl.zetosoftware.user.value_objects.EmailValidator;
import pl.zetosoftware.user.value_objects.LoginValidator;

import java.util.ArrayList;

@Service
public class UserRegistrationService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserRegistrationService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserRegisterValidDto register(UserRequestDto userRequestDto) {

        UserRegisterValidDto userRegisterValidDto = new UserRegisterValidDto( new ArrayList<>() );
        if ( existWithEmail(userRequestDto.email()) ) {
            userRegisterValidDto.getErrors().add( new ErrorDto(" User with this email already exists !! ") );
        }
        if ( existWithLogin(userRequestDto.login()) ) {
            userRegisterValidDto.getErrors().add( new ErrorDto(" User with this login already exists !! ") );
        }
        if( userRegisterValidDto.isListOfErrorsEmpty() ) {
            var userEntity = userMapper.fromUserRequestDtoToUserEntity(userRequestDto);
            userRepository.save(userEntity);
        }
        return userRegisterValidDto;
    }

    public Boolean existWithEmail(String email) {
        return userRepository.existsUserEntityByEmail(new EmailValidator(email));
    }

    public Boolean existWithLogin(String login) {
        return userRepository.existsUserEntityByLogin(new LoginValidator(login));
    }

}
