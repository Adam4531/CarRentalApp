package pl.zetosoftware.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/elo")
    public String elo(){
        return "elo";
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody User user){
        return userService.createUser(user);
    }


}
