package pl.zetosoftware.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

    @Query(value = "SELECT * FROM reservations WHERE user_id=?1 ", nativeQuery = true)
    List<ReservationEntity> getAllReservationsByUserId(Long Id);

}
