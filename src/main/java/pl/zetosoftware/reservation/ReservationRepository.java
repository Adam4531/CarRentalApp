package pl.zetosoftware.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.zetosoftware.user.value_objects.EmailValidator;

import java.util.List;
import java.util.Map;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

    @Query(value = "SELECT * FROM reservations WHERE user_id=?1 ", nativeQuery = true)
    List<ReservationEntity> getAllReservationsByUserId(Long Id);

    @Query(value = "SELECT * FROM reservations WHERE car_id=?1 ", nativeQuery = true)
    List<ReservationEntity> getAllReservationsByCarId(Long Id);

    List<ReservationEntity> findAllByUserIdEmail(EmailValidator email);

    ReservationEntity getReservationEntityByUserId(Long id);

    @Query(value = "SELECT COUNT(car_id) FROM reservations GROUP BY car_id ORDER BY car_id", nativeQuery = true)
    List<Integer> getAllCarsByPopularityOfReservations();

    @Query(value = "SELECT COUNT(car_id) FROM reservations WHERE car_id=?1", nativeQuery = true)
    Integer getAllNumberOfReservationsById(Long id);


}
