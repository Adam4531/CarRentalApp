package pl.zetosoftware.reservation.dto;


import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ReservationCarDTO(String brand, String model, Integer productionYear, BigDecimal pricePerDayRent,
                                String status) {
                            //  StatusEnum status
}
