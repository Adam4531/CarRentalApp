package pl.zetosoftware.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import pl.zetosoftware.user.UserEntity;

import java.util.Collection;
import java.util.stream.Collectors;

@Getter
public class MyUserDetails implements UserDetails {

    private final UserEntity user;

    public MyUserDetails(UserEntity user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return user.getPassword().toString();
    }

    @Override
    public String getUsername() {
        return user.getEmail().toString();
    }

    // if user has Id, his account exists, otherwise not
    @Override
    public boolean isAccountNonExpired() {
        return user.getId() != null;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
