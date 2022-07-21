package pl.zetosoftware.car.dto;


import lombok.Builder;
import pl.zetosoftware.car.enums.BodyTypeEnum;
import pl.zetosoftware.car.enums.StatusEnum;
import pl.zetosoftware.car.enums.TypeOfFuelEnum;

import java.math.BigDecimal;

@Builder
public record ReservationCarDto(Long id, String brand, String model,
                                BigDecimal engineCapacity, Integer productionYear,
                                TypeOfFuelEnum typeOfFuelEnum, BodyTypeEnum bodyTypeEnum,
                                BigDecimal pricePerDayRent, StatusEnum status, Long newCarCost) {



}
