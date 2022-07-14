package pl.zetosoftware.security;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.zetosoftware.user.UserEntity;
import pl.zetosoftware.user.UserRepository;
import pl.zetosoftware.user.value_objects.EmailValidator;

import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        UserEntity user = userRepository.findUserByEmail(new EmailValidator(email));
//
//        if(user == null){
//            user = new UserEntity();
//        }
//        return new MyUserDetails(user);
//    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findUserByEmail(new EmailValidator(email));
        if (user == null) {
            throw new UsernameNotFoundException("user with email: " + email + " not found in the database");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail().toString(),
                new BCryptPasswordEncoder().encode(user.getPassword().toString()),
                user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet()));
    }
}
