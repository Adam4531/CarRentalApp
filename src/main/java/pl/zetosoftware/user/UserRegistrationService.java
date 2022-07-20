package pl.zetosoftware.user;

import org.springframework.stereotype.Service;
import pl.zetosoftware.global.dto.ErrorsListDto;
import pl.zetosoftware.user.dto.UserRequestDto;
import pl.zetosoftware.user.value_objects.EmailValidator;
import pl.zetosoftware.user.value_objects.LoginValidator;

import java.util.ArrayList;

@Service
public class UserRegistrationService {

    private static final String POLISH_ALPHABET = "[a-zA-Z-\\p{IsAlphabetic}]+";
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserRegistrationService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public ErrorsListDto register(UserRequestDto userRequestDto) {

        ErrorsListDto errorsListDto = new ErrorsListDto( new ArrayList<>() );

        if ( !emailContainsAtSign(userRequestDto.email()) ) {
            errorsListDto.add(" Email must contains '@' sign !!");
        }
        if ( !passwordIsValidLength(userRequestDto.password()) ) {
            errorsListDto.add(" Password must be at least 7 chars long !!");
        }
        if ( !loginIsValidLength(userRequestDto.login()) ) {
            errorsListDto.add(" Login must be at least 7 chars long !!");
        }
        if ( !nameContainsValidSigns(userRequestDto.firstName(), userRequestDto.secondName()) ) {
            errorsListDto.add(" First name and second name must contains only letters !!");
        }
        if( errorsListDto.isListOfErrorsEmpty() ) {
            var userEntity = userMapper.fromUserRequestDtoToUserEntity(userRequestDto);
            userRepository.save(userEntity);
        }

        return errorsListDto;
    }

    // komentujemy to, bo na frontend przechodzi exception zwiazany z EmailValidatorem
    // rozwiazaniem moze byc napisanie custom query
    // albo wrzucenie validatorow pod frontend, takich jak nizej
//    public Boolean existWithEmail(String email) {
//        return userRepository.existsUserEntityByEmail(new EmailValidator(email));
//    }
//
//    public Boolean existWithLogin(String login) {
//        return userRepository.existsUserEntityByLogin(new LoginValidator(login));
//    }
    public Boolean loginIsValidLength(String login) {
        return login != null && login.length() > 6;
    }

    public Boolean emailContainsAtSign(String email) {
        return email != null && email.contains("@");
    }

    public Boolean passwordIsValidLength(String password) {
        return password != null && password.length() > 6;
    }

    public Boolean nameContainsValidSigns(String firstName, String secondName) {
        return firstName != null && firstName.matches(POLISH_ALPHABET) &&
                secondName != null && secondName.matches(POLISH_ALPHABET);
    }

}
