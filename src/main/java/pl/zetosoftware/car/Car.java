package pl.zetosoftware.car;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import pl.zetosoftware.car.enums.BodyTypeEnum;
import pl.zetosoftware.car.enums.TypeOfFuel;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Table(name = "CARS")
public class Car implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    private String brand;

    @Enumerated(EnumType.STRING)
    private String model;

    @NotNull
    private Double engineCapacity;

    private BodyTypeEnum bodyTypeEnum;

    private TypeOfFuel typeOfFuel;

    private Double newCarCost;

    private Integer productionYear;

}
