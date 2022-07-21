package pl.zetosoftware.car.dto;


import lombok.Builder;
import pl.zetosoftware.car.enums.BodyTypeEnum;
import pl.zetosoftware.car.enums.StatusEnum;
import pl.zetosoftware.car.enums.TypeOfFuelEnum;

import java.math.BigDecimal;

@Builder
public record CarDto(Long id, String brand, String model,
                     BigDecimal engineCapacity, BodyTypeEnum bodyTypeEnum,
                     TypeOfFuelEnum typeOfFuelEnum, BigDecimal pricePerDayRent,
                     StatusEnum status, Integer productionYear) {



}
