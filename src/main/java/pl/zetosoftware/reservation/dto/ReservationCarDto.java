package pl.zetosoftware.reservation.dto;


import lombok.Builder;
import pl.zetosoftware.car.enums.BodyTypeEnum;

import java.math.BigDecimal;

@Builder
public record ReservationCarDto(String brand, String model, BigDecimal engineCapacity, Integer productionYear,
                                BigDecimal pricePerDayRent, BodyTypeEnum bodyTypeEnum, String status) {
                                                                                    //  StatusEnum status
}
