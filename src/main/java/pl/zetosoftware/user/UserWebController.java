package pl.zetosoftware.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.zetosoftware.global.dto.ErrorsListDto;
import pl.zetosoftware.security.SessionRegistry;
import pl.zetosoftware.user.dto.UserLoginDto;
import pl.zetosoftware.user.dto.UserLoginResponseDto;
import pl.zetosoftware.user.dto.UserRequestDto;

@RestController
@RequestMapping("/api")
public class UserWebController {

    @Autowired
    public AuthenticationManager manager;
    @Autowired
    public SessionRegistry sessionRegistry;
    @Autowired
    private UserLoginService userLoginService;
    @Autowired
    private UserRegistrationService userRegistrationService;

    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserLoginResponseDto> login(@RequestBody UserLoginDto user) {
        return ResponseEntity.ok(userLoginService.login(user));
    }

    @PostMapping("/registration")
    public ErrorsListDto create(@RequestBody UserRequestDto user) {
        return userRegistrationService.register(user);
    }
}
