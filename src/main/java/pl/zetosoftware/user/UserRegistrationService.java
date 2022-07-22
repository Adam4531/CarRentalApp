package pl.zetosoftware.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zetosoftware.global.dto.ErrorsListDto;
import pl.zetosoftware.user.dto.UserRequestDto;
import pl.zetosoftware.user.value_objects.EmailValidator;
import pl.zetosoftware.user.value_objects.LoginValidator;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserRegistrationService {

    private static final String POLISH_ALPHABET = "[a-zA-Z-\\p{IsAlphabetic}]+";
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public ErrorsListDto register(UserRequestDto userRequestDto) {

        ErrorsListDto errorsListDto = new ErrorsListDto( new ArrayList<>() );

        if ( !emailContainsAtSign(userRequestDto.email()) ) {
            errorsListDto.add("Email must contains '@' sign!");
        }
        if ( !isEmailUnique(userRequestDto.email()) ) {
            errorsListDto.add("User with this email already exists!");
        }
        if ( !passwordIsValidLength(userRequestDto.password()) ) {
            errorsListDto.add("Password must be at least 7 chars long!");
        }
        if ( !loginIsValidLength(userRequestDto.login()) ) {
            errorsListDto.add("Login must be at least 7 chars long!");
        }
        if ( !nameContainsValidSigns(userRequestDto.firstName(), userRequestDto.secondName()) ) {
            errorsListDto.add("First name and second name must contains only letters!");
        }
        if ( !isAnAdult(userRequestDto.pesel())){
            errorsListDto.add("You need to be an adult to use our service!");
        }
        if( errorsListDto.isListOfErrorsEmpty() ) {
            var userEntity = userMapper.fromUserRequestDtoToUserEntity(userRequestDto);
            userRepository.save(userEntity);
        }
        return errorsListDto;
    }

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

    public Boolean isAnAdult(String pesel){
        return pesel != null && LocalDate.now().getYear() - yearOfBirth(pesel) >= 18;
    }

    public int yearOfBirth (String pesel){
        int lastTwoDigitsYearOfBirth = Integer.parseInt(pesel.substring(0,2));
        int codedCentury = Integer.parseInt(pesel.substring(2,4));
        int century = 1800;  //Powinno byc > 80 && < 93, ale nie robimy walidacji calego peselu

        if (codedCentury < 13) century = 1900;  //Powinno byc > 0 && < 13, ale nie robimy walidacji calego peselu
        if (codedCentury > 12 && codedCentury < 33) century = 2000; //Powinno byc > 20 && < 33, ale nie robimy walidacji calego peselu

        return century + lastTwoDigitsYearOfBirth;
    }

    public boolean isEmailUnique(String email){
        final UserEntity user = userRepository.findUserByEmail(new EmailValidator(email));
        return user == null;
    }
}
