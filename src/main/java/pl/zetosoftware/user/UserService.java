package pl.zetosoftware.user;

import org.springframework.stereotype.Service;
import pl.zetosoftware.user.dtos.UserDto;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    public List<UserDto> getAllUsers() {
        var users = userRepository.findAll();
        return userMapper.fromUserListToUserDtoList(users);
    }

    public User getUser(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User with id: " + id + "does not exist!"));
    }

    public UserDto getUserById(Long id) {
        var user = getUser(id);
        return userMapper.fromUserToUserDTO(user);
    }

}
