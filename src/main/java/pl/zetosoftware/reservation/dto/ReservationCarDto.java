package pl.zetosoftware.reservation.dto;


import lombok.Builder;
import pl.zetosoftware.car.enums.BodyTypeEnum;
import pl.zetosoftware.car.enums.StatusEnum;

import java.math.BigDecimal;

@Builder
public record ReservationCarDto(String brand, String model, BigDecimal engineCapacity, Integer productionYear,
                                BigDecimal pricePerDayRent, BodyTypeEnum bodyTypeEnum, StatusEnum status) {

}
