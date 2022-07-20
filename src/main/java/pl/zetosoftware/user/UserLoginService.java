package pl.zetosoftware.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import pl.zetosoftware.security.SessionRegistry;
import pl.zetosoftware.user.dto.*;

@Service
public class UserLoginService {

    @Autowired
    public AuthenticationManager manager;
    @Autowired
    public SessionRegistry sessionRegistry;

    public UserLoginResponseDto login(UserLoginDto user) {
        manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(), user.getPassword()
                ));

        UserLoginResponseDto response = new UserLoginResponseDto();
        final String sessionId = sessionRegistry.registerSession(user.getEmail());
        response.setSessionId(sessionId);
        return response;
    }

}
