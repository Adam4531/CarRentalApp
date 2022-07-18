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
                .id(carEntity.getId())
                .brand(carEntity.getBrand().toString())
                .model(carEntity.getModel().toString())
                .engineCapacity(carEntity.getEngineCapacity().toBigDecimal())
                .bodyTypeEnum(carEntity.getBodyType())
                .typeOfFuelEnum(carEntity.getTypeOfFuel())
                .newCarCost(carEntity.getNewCarCost().toLong())
                .productionYear(carEntity.getProductionYear().toInteger())
                .build();
    }

    public List<CarDto> mapCarListToCarListDto(List<CarEntity> carEntities) {
        return carEntities
                .stream()
                .map(this::mapCarToCarDto)
                .collect(Collectors.toList());
    }
}
