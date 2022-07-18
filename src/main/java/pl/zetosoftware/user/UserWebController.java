package pl.zetosoftware.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.zetosoftware.security.InMemorySession;
import pl.zetosoftware.user.dto.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserWebController {

    private final UserRegistrationService userRegistrationService;
    @Autowired
    public AuthenticationManager manager;
    @Autowired
    public InMemorySession inMemorySession;

    public UserWebController(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDTO> login(@RequestBody UserLoginDto user) {
        manager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        final String sessionId = inMemorySession.registerSession(user.getEmail());
        UserLoginResponseDTO response = new UserLoginResponseDTO();
        response.setSessionId(sessionId);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/registration")
    public UserRegisterValidDto create(@RequestBody UserRequestDto user) {
        return userRegistrationService.register(user);
    }
}
