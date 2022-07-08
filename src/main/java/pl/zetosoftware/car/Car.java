package pl.zetosoftware.car;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.zetosoftware.car.enums.BodyType;
import pl.zetosoftware.car.enums.TypeOfFuel;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "CARS")
public class Car implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String brand;

    private String model;

    private BigDecimal engineCapacity;

    @Enumerated(EnumType.STRING)
    private BodyType bodyType;

    @Enumerated(EnumType.STRING)
    private TypeOfFuel typeOfFuel;

    @Column(name = "new_car_cost")
    private BigDecimal newCarCost;

    @Column(name = "production_year")
    private Integer productionYear;

}
