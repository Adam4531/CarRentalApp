package pl.zetosoftware.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.zetosoftware.car.dto.CarDto;
import pl.zetosoftware.reservation.ReservationService;
import pl.zetosoftware.reservation.dto.ReservationCarDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarMapper {

    @Autowired
    CarService carService;


    public CarDto mapCarToCarDto(CarEntity carEntity) {
        return CarDto.builder()
                .brand(carEntity.getBrand().toString())
                .model(carEntity.getModel().toString())
                .engineCapacity(carEntity.getEngineCapacity().toBigDecimal())
                .bodyTypeEnum(carEntity.getBodyType())
                .typeOfFuelEnum(carEntity.getTypeOfFuel())
                .newCarCost(carEntity.getNewCarCost().toLong())
                .productionYear(carEntity.getProductionYear().toInteger())
                .statusEnum(carEntity.getStatus())
                .build();
    }

    public List<CarDto> mapCarListToCarListDto(List<CarEntity> carEntities) {
        return carEntities
                .stream()
                .map(this::mapCarToCarDto)
                .collect(Collectors.toList());
    }

    public ReservationCarDto fromCarEntityToReservationCarDto(CarEntity carEntity){
        return ReservationCarDto.builder()
                .brand(carEntity.getBrand().toString())
                .model(carEntity.getModel().toString())
                .engineCapacity(carEntity.getEngineCapacity().toBigDecimal())
                .bodyTypeEnum(carEntity.getBodyType())
                .status(carService.refreshStatus(carEntity.getId()))
                .pricePerDayRent(carService.initialPrice(carEntity.getId()))
                .build();
    }

    public List<ReservationCarDto> fromCarEntityListToReservationCarDtoList(List<CarEntity> carEntityList){
        return carEntityList.stream()
                .map(this::fromCarEntityToReservationCarDto)
                .collect(Collectors.toList());
    }



}
