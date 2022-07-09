package pl.zetosoftware.car;

import org.springframework.stereotype.Component;
import pl.zetosoftware.car.dto.CarDto;
import pl.zetosoftware.car.enums.BodyType;
import pl.zetosoftware.car.enums.TypeOfFuel;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarMapper {

    public CarDto mapCarToCarDto(Car car){
        return CarDto.builder()
                .id(car.getId())
                .brand(car.getBrand().toString())
                .model(car.getModel().toString())
                .engineCapacity(car.getEngineCapacity().toBigDecimal())
                .bodyType(car.getBodyType())
                .typeOfFuel(car.getTypeOfFuel())
                .newCarCost(car.getNewCarCost().toLong())
                .productionYear(car.getProductionYear().toInteger())
                .build();
    }

    public List<CarDto> mapCarListToCarListDto(List<Car> cars){
        return cars
                .stream()
                .map(this::mapCarToCarDto)
                .collect(Collectors.toList());
    }

}
