package pl.zetosoftware.car;

import org.springframework.stereotype.Component;
import pl.zetosoftware.car.dto.CarDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarMapper {
    public CarDto mapCarToCarDto(CarEntity carEntity) {
        return CarDto.builder()
                .brand(carEntity.getBrand().toString())
                .model(carEntity.getModel().toString())
                .engineCapacity(carEntity.getEngineCapacity().toBigDecimal())
                .bodyType(carEntity.getBodyType())
                .typeOfFuel(carEntity.getTypeOfFuel())
                .newCarCost(carEntity.getNewCarCost().toLong())
                .productionYear(carEntity.getProductionYear().toInteger())
                .status(carEntity.getStatus())
                .build();
    }

    public List<CarDto> mapCarListToCarListDto(List<CarEntity> carEntities) {
        return carEntities
                .stream()
                .map(this::mapCarToCarDto)
                .collect(Collectors.toList());
    }
}
