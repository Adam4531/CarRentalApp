package pl.zetosoftware.user;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.zetosoftware.user.dto.UserLoginDto;
import pl.zetosoftware.global.dto.ErrorsListDto;
import pl.zetosoftware.user.dto.UserRequestDto;

@RestController
@RequestMapping("")
public class UserWebController {

    private final UserRegistrationService userRegistrationService;

    public UserWebController(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @PostMapping("/register")
    public ErrorsListDto create(@RequestBody UserRequestDto user) {
        return userRegistrationService.register(user);
    }

}
