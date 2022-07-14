package pl.zetosoftware.user;

import java.util.logging.Logger;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import lombok.Getter;

@Getter
public class UserCriteriaBuilder {

    private final Root<UserEntity> user;
    private final CriteriaBuilder cb;
    private Predicate predicate;

    public UserCriteriaBuilder(Root<UserEntity> user, CriteriaBuilder cb) {
        this.user = user;
        this.cb = cb;
    }

    public UserCriteriaBuilder addCriteria(String fieldName, Object value) {
        if (value == null) {
            return this;
        }
        switch (fieldName) {
            case "id":
                if (predicate == null) {
                    predicate = cb.equal(user.get("id"), value);
                } else {
                    predicate = cb.and(predicate, cb.equal(user.get("id"), value));
                }
                break;
            case "firstName":
                if (predicate == null) {
                    predicate = cb.equal(user.get("firstName"), value);
                } else {
                    predicate = cb.and(predicate, cb.equal(user.get("firstName"), value));
                }
                break;
            case "lastName":
                if (predicate == null) {
                    predicate = cb.equal(user.get("lastName"), value);
                } else {
                    predicate = cb.and(predicate, cb.equal(user.get("lastName"), value));
                }
                break;
            case "birth":
                if (predicate == null) {
                    predicate = cb.equal(user.get("birth"), value);
                } else {
                    predicate = cb.and(predicate, cb.equal(user.get("birth"), value));
                }
                break;
            case "email":
                if (predicate == null) {
                    predicate = cb.like(user.get("email"), (String) value);
                } else {
                    predicate = cb.and(predicate, cb.like(user.get("email"), (String) value));
                }
                break;
            case "activated":
                if (predicate == null) {
                    predicate = cb.equal(user.get("activated"), value);
                } else {
                    predicate = cb.and(predicate, cb.equal(user.get("activated"), value));
                }
                break;
            case "deleted":
                if (predicate == null) {
                    predicate = cb.equal(user.get("deleted"), value);
                } else {
                    predicate = cb.and(predicate, cb.equal(user.get("deleted"), value));
                }
                break;
        }
        return this;
    }
}
