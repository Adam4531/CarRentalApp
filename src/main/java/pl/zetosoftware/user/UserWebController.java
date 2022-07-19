package pl.zetosoftware.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import pl.zetosoftware.security.SessionRegistry;
import pl.zetosoftware.user.dto.*;

@RestController
@RequestMapping("/api")
public class UserWebController {

//    private final UserRegistrationService userRegistrationService;
    @Autowired
    public AuthenticationManager manager;
    @Autowired
    public SessionRegistry sessionRegistry;

//    public UserWebController(UserRegistrationService userRegistrationService) {
//        this.userRegistrationService = userRegistrationService;
//    }

    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserLoginResponseDTO> login(@RequestBody UserLoginDto user) {
        manager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
        );

        final String sessionId = sessionRegistry.registerSession(user.getEmail());
        UserLoginResponseDTO response = new UserLoginResponseDTO();
        response.setSessionId(sessionId);

        return ResponseEntity.ok(response);
    }

//    @PostMapping("/registration")
//    public UserRegisterValidDto create(@RequestBody UserRequestDto user) {
//        return userRegistrationService.register(user);
//    }
}
