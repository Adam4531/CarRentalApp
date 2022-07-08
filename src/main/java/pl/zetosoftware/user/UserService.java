package pl.zetosoftware.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.zetosoftware.user.dtos.UserDto;

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

    public UserDto createUser(User user){
        userRepository.save(user);
        return userMapper.fromUserToUserDTO(user);
    }

    public List<UserDto> getAllUsers() {
        var users = userRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return userMapper.fromUserListToUserDtoList(users);
    }

    public User getUser(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User with id: " + id + " does not exist!"));
    }

    public UserDto getUserById(Long id) {
        var user = getUser(id);
        return userMapper.fromUserToUserDTO(user);
    }

    public UserDto updateUserWithPutMapping(Long id, User updatedUser) {
        var userToBeChanged = getUser(id);

        userToBeChanged.changeLogin(updatedUser.getLogin());
        userToBeChanged.changeEmail(updatedUser.getEmail());
        userToBeChanged.changeFirstName(updatedUser.getFirstName());
        userToBeChanged.changeSecondName(updatedUser.getSecondName());
        userToBeChanged.changePhoneNumber(updatedUser.getPhoneNumber());
        userToBeChanged.changePesel(updatedUser.getPesel());
        userToBeChanged.changePassword(updatedUser.getPassword());

        userRepository.save(userToBeChanged);
        return userMapper.fromUserToUserDTO(userToBeChanged);
    }

    public UserDto updateUserEmail(Long id, User userWithNewEmail) {
        var user = getUser(id);
        user.changeEmail(userWithNewEmail.getEmail());
        userRepository.save(user);
        return userMapper.fromUserToUserDTO(user);
    }

    public String deleteUserById(Long id) {
        var user = getUser(id);
        userRepository.delete(user);
        return "User with id: " + id + " deleted successfully!";
    }

}
