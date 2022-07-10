package pl.zetosoftware.car.dto;

import lombok.Builder;
import pl.zetosoftware.car.enums.BodyType;
import pl.zetosoftware.car.enums.TypeOfFuel;

import java.math.BigDecimal;

@Builder
public record CarDto(String brand, String model,
                     BigDecimal engineCapacity, Enum<BodyType> bodyType,
                     Enum<TypeOfFuel> typeOfFuel, Long newCarCost, Integer productionYear) {

}
