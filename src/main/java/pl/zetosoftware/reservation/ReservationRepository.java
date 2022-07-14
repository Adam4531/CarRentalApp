package pl.zetosoftware.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

    @Query(value = "SELECT * FROM reservations WHERE user_id=?1 ", nativeQuery = true)
    List<ReservationEntity> getAllReservationsByUserId(Long Id);

    @Query(value = "SELECT * FROM reservations WHERE car_id=?1 ", nativeQuery = true)
    List<ReservationEntity> getAllReservationsByCarId(Long Id);

    @Query(value = "SELECT car_id, COUNT(car_id) FROM reservations GROUP BY car_id ORDER BY car_id ASC", nativeQuery = true)
    Map<Integer, Integer> getAllCarsByPopularityOfReservations();

}
