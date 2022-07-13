package pl.zetosoftware.reservation;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class ReservationEditorValidator {
    private final ReservationRepository reservationRepository;

    public ReservationEditorValidator(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public boolean datesEquals(LocalDate dateStart, LocalDate dateEnd, LocalDate reservationDateStart, LocalDate reservationDateEnd){
        return dateStart.equals(reservationDateStart) || dateStart.equals(reservationDateEnd)
                || dateEnd.equals(reservationDateStart) || dateEnd.equals(reservationDateEnd);
    }

    public boolean dateBetween(LocalDate dateBetween, LocalDate firstDate, LocalDate secondDate){
        return dateBetween.isAfter(firstDate) && dateBetween.isBefore(secondDate);
    }

    public boolean reservationContainsNewReservation(LocalDate dateStart, LocalDate dateEnd, LocalDate reservationDateStart, LocalDate reservationDateEnd){
        return reservationDateStart.isBefore(dateStart) && reservationDateEnd.isAfter(dateEnd);
    }

//Nie działa jeśli zmieniamy czas rezerwacji, który zawiera się w aktualnie zmienianej rezerwacji:
//trzeba usunąć akutalnie zmienianą rezerwację z listy
    public boolean isReservationAvailable(Long carId, LocalDate dateStart, LocalDate dateEnd){
        List<ReservationEntity> reservationEntities = reservationRepository.findAll();
        reservationEntities.removeIf(reservation -> !reservation.getCarId().getId().equals(carId));

        for (ReservationEntity reservation: reservationEntities) {
            if ((datesEquals (dateStart, dateEnd, reservation.getDate().getDateStart(), reservation.getDate().getDateEnd()))
            || (dateBetween (reservation.getDate().getDateEnd(), dateStart, dateEnd))
            || (dateBetween (reservation.getDate().getDateStart(), dateStart, dateEnd)
            || (reservationContainsNewReservation (dateStart, dateEnd, reservation.getDate().getDateStart(), reservation.getDate().getDateEnd()))))
                return false;
        }
        return true;

//        for (ReservationEntity reservation: reservationEntities) {
//            if ((dateStart.equals(reservation.getDate().getDateStart()) || dateStart.equals(reservation.getDate().getDateEnd())
//                    || dateEnd.equals(reservation.getDate().getDateStart()) || dateEnd.equals(reservation.getDate().getDateEnd()))
//            || (reservation.getDate().getDateEnd().isAfter(dateStart) && reservation.getDate().getDateEnd().isBefore(dateEnd))
//            || (reservation.getDate().getDateStart().isAfter(dateStart) && reservation.getDate().getDateStart().isBefore(dateEnd))
//            || (reservation.getDate().getDateStart().isBefore(dateStart) && reservation.getDate().getDateEnd().isAfter(dateEnd)))
    }
}
