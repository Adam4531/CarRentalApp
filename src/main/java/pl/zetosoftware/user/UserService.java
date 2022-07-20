package pl.zetosoftware.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.zetosoftware.user.dto.UserRequestDto;
import pl.zetosoftware.user.dto.UserResponseDto;
import pl.zetosoftware.user.value_objects.EmailValidator;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserResponseDto> getAllUsers() {
        var users = userRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return userMapper.fromUserEntityListToUserResponseList(users);
    }

    private UserEntity getUser(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("UserEntity with id: " + id + " does not exist!"));
    }

    public UserResponseDto getUserById(Long id) {
        var user = getUser(id);
        return userMapper.fromUserEntityToUserResponseDto(user);
    }

    public UserResponseDto updateUserWithPutMapping(Long id, UserRequestDto updatedUser) {//TODO uzupełnić
        var userToBeChanged = getUser(id);

        userToBeChanged.changeEmail(updatedUser.email());
        userToBeChanged.changeFirstName(updatedUser.firstName());
        userToBeChanged.changeSecondName(updatedUser.secondName());
        userToBeChanged.changePhoneNumber(updatedUser.phoneNumber());
        userToBeChanged.changeLogin(updatedUser.login());
        userToBeChanged.changePesel(updatedUser.pesel());

        userRepository.save(userToBeChanged);
        return userMapper.fromUserEntityToUserResponseDto(userToBeChanged);
    }

    public UserResponseDto updateUserEmail(Long id, String email) {
        var user = getUser(id);
        user.changeEmail(email);
        userRepository.save(user);
        return userMapper.fromUserEntityToUserResponseDto(user);
    }

    public String deleteUserById(Long id) {
        var user = getUser(id);
        userRepository.delete(user);
        return "User with id: " + id + " deleted successfully!";
    }

    //do usuniecia?
    public UserResponseDto getCurrentLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String email = authentication.getName();
            UserEntity user = userRepository.findUserByEmail(new EmailValidator(email));
            return userMapper.fromUserEntityToUserResponseDto(user);
        }
        return null;
    }

}
