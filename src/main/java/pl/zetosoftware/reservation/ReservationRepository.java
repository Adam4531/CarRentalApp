package pl.zetosoftware.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.zetosoftware.user.UserEntity;
import pl.zetosoftware.user.value_objects.EmailValidator;

import java.util.List;
import java.util.Map;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {


    List<ReservationEntity> findAllByUserId(UserEntity userId);

    @Query(value = "SELECT * FROM reservations WHERE car_id=?1 ", nativeQuery = true)
    List<ReservationEntity> getAllReservationsByCarId(Long id);

    List<ReservationEntity> findAllByUserIdEmail(EmailValidator email);

    @Query(value = "SELECT car_id, COUNT(car_id) FROM reservations RIGHT JOIN cars ON cars.id=reservations.car_id GROUP BY car_id ORDER BY car_id", nativeQuery = true)
    Map<Long, Integer> getCarsWithNumberOfReservations();

    ReservationEntity getReservationEntityByUserId(Long id);


}
