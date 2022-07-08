package pl.zetosoftware.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zetosoftware.user.value_objects.Name;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {



}
