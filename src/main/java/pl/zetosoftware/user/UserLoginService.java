package pl.zetosoftware.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import pl.zetosoftware.global.dto.ErrorsListDto;
import pl.zetosoftware.security.SessionRegistry;
import pl.zetosoftware.user.dto.UserLoginDto;
import pl.zetosoftware.user.dto.UserLoginResponseDto;

import java.util.ArrayList;

@Service
public class UserLoginService {

    private final AuthenticationManager manager;
    private final SessionRegistry sessionRegistry;

    public UserLoginService(AuthenticationManager manager, SessionRegistry sessionRegistry) {
        this.manager = manager;
        this.sessionRegistry = sessionRegistry;
    }

    public UserLoginResponseDto login(UserLoginDto user) {
        UserLoginResponseDto response = new UserLoginResponseDto(new ErrorsListDto(new ArrayList<>()));

        try {
            manager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
            final String sessionId = sessionRegistry.registerSession(user.getEmail());
            response.setSessionId(sessionId);
        }
        catch (BadCredentialsException | InternalAuthenticationServiceException exception) {
            if ( !emailContainsAtSign(user.getEmail()) ) {
                response.addToErrorList(" Email must contain '@' sign ! ");
            }
            response.addToErrorList(" You have entered bad credentials, try again !");
        }

        return response;
    }

    public Boolean emailContainsAtSign(String email) {
        return email != null && email.contains("@");
    }

}
