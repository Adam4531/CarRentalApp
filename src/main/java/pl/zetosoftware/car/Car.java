package pl.zetosoftware.car;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.zetosoftware.car.enums.BodyType;
import pl.zetosoftware.car.enums.TypeOfFuel;
import pl.zetosoftware.car.value_objects.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "CARS")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Embedded
    private Brand brand;

    @Embedded
    private Model model;

    @Embedded
    private EngineCapacity engineCapacity;

    @Embedded
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "NUMERIC(19,0)")
    private BodyType bodyType;

    @Embedded
    @Enumerated(EnumType.STRING)
    private TypeOfFuel typeOfFuel;

    @Embedded
    private NewCarCost newCarCost;

    @Embedded
    private ProductionYear productionYear;

}
