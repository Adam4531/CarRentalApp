package pl.zetosoftware.car;


import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.zetosoftware.car.enums.BodyType;
import pl.zetosoftware.car.enums.TypeOfFuel;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "cars")
@NoArgsConstructor
@Getter
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private String brand;
    private String model;

    @Column(name = "engine_capacity")
    private double engineCapacity;

    @Column(name = "body_type")
    private BodyType bodyType;

    @Column(name = "type_of_fuel")
    private TypeOfFuel typeOfFuel;

    @Column(name = "new_car_cost")
    private BigDecimal newCarCost;

    @Column(name = "production_year")
    private Integer productionYear;

}
