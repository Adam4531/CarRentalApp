package pl.zetosoftware.user;


import pl.zetosoftware.user.dto.UserRequestDto;
import pl.zetosoftware.user.dto.UserSearchDto;

import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class UserSearchRepositoryImpl implements UserSearchRepository{

    private final EntityManager entityManager;

    public UserSearchRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<UserEntity> find(UserSearchDto userSearchDto) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserEntity> cq = cb.createQuery(UserEntity.class);
        Root<UserEntity> user = cq.from(UserEntity.class);
        UserCriteriaBuilder userCriteriaBuilder = new UserCriteriaBuilder(user, cb);

        userCriteriaBuilder.addCriteria("id", userSearchDto.getId())
                .addCriteria("firstName", userSearchDto.getFirstName())
                .addCriteria("lastName", userSearchDto.getSecondName())
                .addCriteria("email", userSearchDto.getEmail())
                .addCriteria("activated", userSearchDto.getActivated());

        Predicate predicate = userCriteriaBuilder.getPredicate();
        List<UserEntity> userList;
        if (predicate == null) {
            userList = entityManager.createQuery(cq.select(user).distinct(true)).getResultList();
        } else {
            userList =
                    entityManager.createQuery(cq.select(user).where(predicate).distinct(true)).getResultList();
        }
        return userList;
    }
}
