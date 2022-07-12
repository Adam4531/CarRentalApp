package pl.zetosoftware.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zetosoftware.user.value_objects.Email;
import pl.zetosoftware.user.value_objects.Login;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Boolean existsUserEntityByEmail(Email email);
    Boolean existsUserEntityByLogin(Login login);

}
