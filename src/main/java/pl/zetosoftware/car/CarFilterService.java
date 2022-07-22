package pl.zetosoftware.car;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zetosoftware.car.CarEntity;
import pl.zetosoftware.car.CarMapper;
import pl.zetosoftware.car.dto.CarDto;
import pl.zetosoftware.car.dto.CarFilterDto;
import pl.zetosoftware.car.value_objects.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class CarFilterService {

    private final CarMapper carMapper;
    private final EntityManagerFactory entityManagerFactory;

    public List<CarDto> getFilteredCars(CarFilterDto carFilterDto) {
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(CarEntity.class);
        Root<CarEntity> root = criteriaQuery.from(CarEntity.class);

        List<Predicate> predicates = getPredicates(carFilterDto, criteriaBuilder, root);

        criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        criteriaQuery.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
        var cars = entityManager.createQuery(criteriaQuery).getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return carMapper.mapCarListToCarListDto(cars);
    }

    private List<Predicate> getPredicates(CarFilterDto carFilterDto, CriteriaBuilder criteriaBuilder, Root<CarEntity> root) {
        var predicates = new ArrayList<Predicate>();

        if( carFilterDto.brand() != null ) {
            var rootBrand = root.get(CarEntity_.brand);
            var carFilterBrand = carFilterDto.brand().toLowerCase().trim();

            predicates.add(criteriaBuilder.equal(rootBrand, new BrandValidator(carFilterBrand)));
        }
        if( carFilterDto.model() != null ) {
            var rootModel = root.get(CarEntity_.model);
            var carFilterModel = carFilterDto.model().toLowerCase().trim();
            predicates.add(criteriaBuilder.equal(rootModel, new ModelValidator(carFilterModel)));
        }
        if( carFilterDto.engineCapacity() != null ) {
            predicates.add(criteriaBuilder.equal(root.get(CarEntity_.engineCapacity), new EngineCapacityValidator(carFilterDto.engineCapacity())));
        }
        if( carFilterDto.bodyType() != null ) {
            predicates.add(criteriaBuilder.equal(root.get(CarEntity_.bodyType), carFilterDto.bodyType()));
        }
        if( carFilterDto.typeOfFuel() != null ) {
            predicates.add(criteriaBuilder.equal(root.get(CarEntity_.typeOfFuel), carFilterDto.typeOfFuel()));
        }
        if( carFilterDto.productionYear() != null ) {
            predicates.add(criteriaBuilder.equal(root.get(CarEntity_.productionYear), new ProductionYearValidator(carFilterDto.productionYear())));
        }
        // searching to see if car is free between two dates to add
        // add error list

        return predicates;
    }

}
