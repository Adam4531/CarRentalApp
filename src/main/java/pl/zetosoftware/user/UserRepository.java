package pl.zetosoftware.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zetosoftware.user.value_objects.EmailValidator;
import pl.zetosoftware.user.value_objects.LoginValidator;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findUserEntityById(Long id);

    Boolean existsUserEntityByEmail(EmailValidator email);
    Boolean existsUserEntityByLogin(LoginValidator login);

    UserEntity findUserByEmail(EmailValidator email);

}
