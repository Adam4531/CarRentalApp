package pl.zetosoftware.car;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zetosoftware.car.dto.CarDto;
import pl.zetosoftware.car.dto.CarFilterDto;
import pl.zetosoftware.car.value_objects.BrandValidator;
import pl.zetosoftware.car.value_objects.EngineCapacityValidator;
import pl.zetosoftware.car.value_objects.ModelValidator;
import pl.zetosoftware.car.value_objects.ProductionYearValidator;
import pl.zetosoftware.reservation.ReservationEntity;
import pl.zetosoftware.reservation.ReservationEntity_;
import pl.zetosoftware.reservation.value_objects.ReservationDatesValidator_;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class CarFilterService {

    private final CarMapper carMapper;
    private final EntityManagerFactory entityManagerFactory;

    public List<CarDto> getFilteredCars(CarFilterDto carFilterDto) {
        final var entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(CarEntity.class);
        var cars = criteriaQuery.from(CarEntity.class);

        var predicates = getPredicates(carFilterDto, criteriaBuilder, cars, criteriaQuery);
        criteriaQuery.select(cars).where(predicates.toArray(new Predicate[predicates.size()]));

        var filteredCars = entityManager.createQuery(criteriaQuery).getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return carMapper.mapCarListToCarListDto(filteredCars);
    }

    private List<Predicate> getPredicates(CarFilterDto carFilterDto, CriteriaBuilder criteriaBuilder, Root<CarEntity> cars, CriteriaQuery<CarEntity> criteriaQuery) {
        var predicates = new ArrayList<Predicate>();

        if( carFilterDto.brand() != null ) {
            var carFilterBrand = carFilterDto.brand().toLowerCase().trim();
            var brand = new BrandValidator(carFilterBrand);
            predicates.add(criteriaBuilder.equal(cars.get(CarEntity_.brand), brand));
        }
        if( carFilterDto.model() != null ) {
            var carFilterModel = carFilterDto.model().toLowerCase().trim();
            var model = new ModelValidator(carFilterModel);
            predicates.add(criteriaBuilder.equal(cars.get(CarEntity_.model), model));
        }
        if( carFilterDto.engineCapacity() != null ) {
            var engineCapacity = new EngineCapacityValidator(carFilterDto.engineCapacity());
            predicates.add(criteriaBuilder.equal(cars.get(CarEntity_.engineCapacity), engineCapacity));
        }
        if( carFilterDto.bodyType() != null ) {
            var bodyType = carFilterDto.bodyType().toString();
            predicates.add(criteriaBuilder.like(cars.get(CarEntity_.bodyType).as(String.class), bodyType));
        }
        if( carFilterDto.freeFrom() != null && carFilterDto.freeTo() != null ) {
            var subquery = criteriaQuery.subquery(Long.class);

            var isTaken = isCarReservedInPeriod(carFilterDto, criteriaBuilder, cars);
            subquery.select(cars.get(CarEntity_.id)).from(CarEntity.class);
            subquery.where(isTaken);

            Predicate carIsNotReserved = cars.get(CarEntity_.id).in(subquery).not();
            predicates.add(carIsNotReserved);
        }
        if( carFilterDto.typeOfFuel() != null ) {
            var typeOfFuel = carFilterDto.typeOfFuel().toString();
            predicates.add(criteriaBuilder.like(cars.get(CarEntity_.typeOfFuel).as(String.class), typeOfFuel));
        }
        if( carFilterDto.productionYear() != null ) {
            var productionYear = new ProductionYearValidator(carFilterDto.productionYear());
            predicates.add(criteriaBuilder.equal(cars.get(CarEntity_.productionYear), productionYear));
        }
        //dodac liste errorow
        //problem n+1

        return predicates;
    }

    private Predicate isCarReservedInPeriod(CarFilterDto carFilterDto, CriteriaBuilder criteriaBuilder, Root<CarEntity> cars) {

        ListJoin<CarEntity, ReservationEntity> join = cars.join(CarEntity_.reservationEntity, JoinType.LEFT);
        var reservationDateStart = join.get(ReservationEntity_.date).get(ReservationDatesValidator_.dateStart);
        var reservationDateEnd = join.get(ReservationEntity_.date).get(ReservationDatesValidator_.dateEnd);

        var isStartDateBetweenTwoGivenDates = criteriaBuilder.between(reservationDateStart, carFilterDto.freeFrom(), carFilterDto.freeTo());
        var isEndDateBetweenTwoGivenDates = criteriaBuilder.between(reservationDateEnd, carFilterDto.freeFrom(), carFilterDto.freeTo());

        var isStartDateBeforeGivenDate = criteriaBuilder.lessThanOrEqualTo(reservationDateStart, carFilterDto.freeFrom());
        var isEndDateAfterGivenDate = criteriaBuilder.greaterThanOrEqualTo(reservationDateEnd, carFilterDto.freeTo());
        var isReservedInGivenPeriod = criteriaBuilder.and(isStartDateBeforeGivenDate, isEndDateAfterGivenDate);

        var isCollidingWithOtherReservation = criteriaBuilder.or(isReservedInGivenPeriod, isStartDateBetweenTwoGivenDates, isEndDateBetweenTwoGivenDates);

        return isCollidingWithOtherReservation;
    }

}
