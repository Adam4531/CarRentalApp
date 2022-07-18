package pl.zetosoftware.car;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.zetosoftware.car.enums.BodyTypeEnum;
import pl.zetosoftware.car.enums.StatusEnum;
import pl.zetosoftware.car.enums.TypeOfFuelEnum;
import pl.zetosoftware.car.value_objects.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "CARS")
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Embedded
    private BrandValidator brand;

    @Embedded
    private ModelValidator model;

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
