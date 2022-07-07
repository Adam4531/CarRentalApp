package pl.zetosoftware.user;

import org.springframework.beans.factory.annotation.Autowired;
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

    public List<User> findAllUsers(){ return userRepository.findAll();}

}
