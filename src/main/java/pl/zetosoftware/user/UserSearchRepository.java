package pl.zetosoftware.user;

import pl.zetosoftware.user.dto.UserRequestDto;
import pl.zetosoftware.user.dto.UserSearchDto;

import java.util.List;

public interface UserSearchRepository {

    List<UserEntity> find(UserSearchDto userSearchDto);
}
