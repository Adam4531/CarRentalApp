package pl.zetosoftware.car;


import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.zetosoftware.car.enums.BodyType;
import pl.zetosoftware.car.enums.TypeOfFuel;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "CARS")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "ID")
    private Long id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "engine_capacity")
    private BigDecimal engineCapacity;

    @Enumerated(EnumType.STRING)
    @Column(name = "body_type")
    private BodyType bodyType;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_of_fuel")
    private TypeOfFuel typeOfFuel;

    @Column(name = "new_car_cost")
    private BigDecimal newCarCost;

    @Column(name = "production_year")
    private Integer productionYear;

}
