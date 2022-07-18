package pl.zetosoftware.car.dto;


import lombok.Builder;
import lombok.Getter;
import pl.zetosoftware.car.enums.BodyTypeEnum;
import pl.zetosoftware.car.enums.StatusEnum;

import java.math.BigDecimal;

@Getter
public class ReservationCarDto {

    private final String brand;
    private final String model;
    private final BigDecimal engineCapacity;
    private final Integer productionYear;
    private BigDecimal pricePerDayRent;
    private final BodyTypeEnum bodyTypeEnum;
    private StatusEnum status;

    @Builder
    public ReservationCarDto(String brand, String model, BigDecimal engineCapacity, Integer productionYear,
                             BigDecimal pricePerDayRent, BodyTypeEnum bodyTypeEnum, StatusEnum status
    ) {
        this.brand = brand;
        this.model = model;
        this.engineCapacity = engineCapacity;
        this.productionYear = productionYear;
        this.pricePerDayRent = pricePerDayRent;
        this.bodyTypeEnum = bodyTypeEnum;
        this.status = status;
    }

    public void setStatus(StatusEnum status){
        this.status = status;
    }

    public void setPricePerDayRent(BigDecimal price){
        this.pricePerDayRent = price;
    }
}
