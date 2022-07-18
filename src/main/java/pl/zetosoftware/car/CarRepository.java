package pl.zetosoftware.car;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {

//    @Query(value = "SELECT * FROM reservations WHERE car_id=?1 ", nativeQuery = true)
//    List<ReservationDto> getAllReservationsByCarId(Long Id);

    @Query(value = "SELECT car_id, COUNT(car_id) FROM reservations GROUP BY car_id ORDER BY car_id", nativeQuery = true)
    Map<Integer, Integer> getAllCarsByPopularityOfReservations();

    @Query(value = "SELECT car_id FROM reservations WHERE id=?1", nativeQuery = true)
    Long getCarIdByReservationId(Long Id);
}
