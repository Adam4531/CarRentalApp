package pl.zetosoftware.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.zetosoftware.user.dto.UserDto;

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

    public UserDto createUser(UserEntity userEntity){
        userRepository.save(userEntity);
        return userMapper.fromUserToUserDTO(userEntity);
    }

    public List<UserDto> getAllUsers() {
        var users = userRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return userMapper.fromUserListToUserDtoList(users);
    }

    public UserEntity getUser(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("UserEntity with id: " + id + " does not exist!"));
    }

    public UserDto getUserById(Long id) {
        var user = getUser(id);
        return userMapper.fromUserToUserDTO(user);
    }

    public UserDto updateUserWithPutMapping(Long id, UserDto updatedUser) {
        var userToBeChanged = getUser(id);

        userToBeChanged.changeLogin(updatedUser.login());
        userToBeChanged.changeEmail(updatedUser.email());
        userToBeChanged.changeFirstName(updatedUser.firstName());
        userToBeChanged.changeSecondName(updatedUser.secondName());
        userToBeChanged.changePhoneNumber(updatedUser.phoneNumber());

        userRepository.save(userToBeChanged);
        return userMapper.fromUserToUserDTO(userToBeChanged);
    }

    public UserDto updateUserEmail(Long id, String email) {
        var user = getUser(id);
        user.changeEmail(email);
        userRepository.save(user);
        return userMapper.fromUserToUserDTO(user);
    }

    public String deleteUserById(Long id) {
        var user = getUser(id);
        userRepository.delete(user);
        return "User with id: " + id + " deleted successfully!";
    }

}
