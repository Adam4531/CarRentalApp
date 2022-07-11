package pl.zetosoftware.car;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.zetosoftware.car.enums.BodyType;
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
    @OneToMany(mappedBy = "car_id")
    private Long id;

    @Embedded
    private Brand brand;

    @Embedded
    private Model model;

    @Embedded
    private EngineCapacity engineCapacity;

    @Enumerated(EnumType.STRING)
    private BodyType bodyType;

    @Enumerated(EnumType.STRING)
    private pl.zetosoftware.car.enums.TypeOfFuel typeOfFuel;

    @Embedded
    private NewCarCost newCarCost;

    @Embedded
    private ProductionYear productionYear;

}
