package pl.zetosoftware.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.zetosoftware.user.dto.UserRequestDto;

@RestController
@RequestMapping("/registration")
public class UserRegistrationController {

    private final UserRegistrationService userRegistrationService;

    public UserRegistrationController(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody UserRequestDto user){
        userRegistrationService.register(user);
    }

}
