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

    @Query(value = "SELECT  COUNT(car_id) FROM reservations GROUP BY car_id ORDER BY car_id ASC ", nativeQuery = true)
    List<Integer> getAllCarsByPopularityOfReservations();

    List<ReservationEntity> findAllByUserIdEmail(EmailValidator email);

    @Query(value = "SELECT car_id FROM reservations WHERE id=?1", nativeQuery = true)
    Long getCarIdByReservationId(Long Id);

    ReservationEntity getReservationEntityByUserId(Long id);


}
