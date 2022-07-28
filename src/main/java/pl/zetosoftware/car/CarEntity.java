package pl.zetosoftware.car;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.zetosoftware.car.enums.BodyTypeEnum;
import pl.zetosoftware.car.enums.TypeOfFuelEnum;
import pl.zetosoftware.car.value_objects.*;
import pl.zetosoftware.global.BasicEntity;
import pl.zetosoftware.reservation.ReservationEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "CARS")
public class CarEntity extends BasicEntity {

    @Embedded
    private BrandValidator brand;

    @Embedded
    private ModelValidator model;

    @OneToMany(mappedBy = "carId", fetch = FetchType.LAZY)
    private List<ReservationEntity> reservationEntity;

    @Embedded
    private EngineCapacityValidator engineCapacity;

    @Enumerated(EnumType.STRING)
    private BodyTypeEnum bodyType;

    @Enumerated(EnumType.STRING)
    private TypeOfFuelEnum typeOfFuel;

    @Embedded
    private NewCarCostValidator newCarCost;

    @Embedded
    private ProductionYearValidator productionYear;

}
