package pl.zetosoftware.car.dto;

import lombok.Builder;
import pl.zetosoftware.car.enums.BodyTypeEnum;
import pl.zetosoftware.car.enums.TypeOfFuelEnum;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record CarFilterDto (String brand, String model, BigDecimal engineCapacity,
                            BodyTypeEnum bodyType, TypeOfFuelEnum typeOfFuel, Integer productionYear,
                            LocalDate freeFrom, LocalDate freeTo){

}
