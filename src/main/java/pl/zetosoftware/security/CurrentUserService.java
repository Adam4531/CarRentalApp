package pl.zetosoftware.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.zetosoftware.user.UserEntity;
import pl.zetosoftware.user.UserRepository;
import pl.zetosoftware.user.value_objects.EmailValidator;

@Service
public class CurrentUserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CurrentUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CurrentUser loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findUserByEmail(new EmailValidator(email));
        if(user == null){
            throw new UsernameNotFoundException("Failed to find user with email : " + email);
        }
        return new CurrentUser(user);
    }
}
