package pl.zetosoftware.car;

import org.springframework.stereotype.Component;
import pl.zetosoftware.car.dto.CarDto;
import pl.zetosoftware.car.dto.ReservationCarDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarMapper {


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
                .status(carEntity.getStatus())
//                .pricePerDayRent(carEntity.getPricePerDay())      //to tez ma się odbywać przez kontroler, czy tak jak teraz robie?
                .build();
    }

    public List<ReservationCarDto> fromCarEntityListToReservationCarDtoList(List<CarEntity> carEntityList){
        return carEntityList.stream()
                .map(this::fromCarEntityToReservationCarDto)
                .collect(Collectors.toList());
    }



}
