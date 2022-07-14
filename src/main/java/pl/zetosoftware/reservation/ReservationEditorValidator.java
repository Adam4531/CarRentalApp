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

    public boolean areDatesEquals(LocalDate dateStart, LocalDate dateEnd, LocalDate reservationDateStart, LocalDate reservationDateEnd){
        return dateStart.equals(reservationDateStart) || dateStart.equals(reservationDateEnd)
                || dateEnd.equals(reservationDateStart) || dateEnd.equals(reservationDateEnd);
    }

    public boolean isDateBetween(LocalDate dateBetween, LocalDate firstDate, LocalDate secondDate){
        return dateBetween.isAfter(firstDate) && dateBetween.isBefore(secondDate);
    }

    public boolean isReservationContainingNewReservation(LocalDate dateStart, LocalDate dateEnd, LocalDate reservationDateStart, LocalDate reservationDateEnd){
        return reservationDateStart.isBefore(dateStart) && reservationDateEnd.isAfter(dateEnd);
    }

    public boolean isPresentDayBeforeReservationToChange (ReservationEntity reservationToChange){
        return LocalDate.now().isBefore(reservationToChange.getDate().getDateStart());
    }

    public boolean isNewStartDayReservationAfterToday (LocalDate dateStart){
        return dateStart.isAfter(LocalDate.now());
    }

    public boolean isReservationAvailable(ReservationEntity reservationToChange, LocalDate dateStart, LocalDate dateEnd){
        if (isPresentDayBeforeReservationToChange(reservationToChange)) {
            if (isNewStartDayReservationAfterToday(dateStart)){

                List<ReservationEntity> reservationEntitiesForSpecifiedCar = reservationRepository.findAll();
                reservationEntitiesForSpecifiedCar.removeIf(reservation -> !reservation.getCarId().getId().equals(reservationToChange.getCarId().getId()));
                reservationEntitiesForSpecifiedCar.remove(reservationToChange);

                for (ReservationEntity reservation : reservationEntitiesForSpecifiedCar) {
                    if ((areDatesEquals(dateStart, dateEnd, reservation.getDate().getDateStart(), reservation.getDate().getDateEnd()))
                            || (isDateBetween(reservation.getDate().getDateEnd(), dateStart, dateEnd))
                            || (isDateBetween(reservation.getDate().getDateStart(), dateStart, dateEnd)
                            || (isReservationContainingNewReservation(dateStart, dateEnd, reservation.getDate().getDateStart(), reservation.getDate().getDateEnd()))))
                        return false;
                }
                return true;
            }
            throw new IllegalStateException("Not allowed to change reservation's date to historical or present date!");
        }
        throw new IllegalStateException("Not allowed to change started/completed reservations!");
    }
}
