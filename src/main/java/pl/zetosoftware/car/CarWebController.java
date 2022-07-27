package pl.zetosoftware.car;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.zetosoftware.car.dto.CarFilterDto;
import pl.zetosoftware.car.dto.CarDto;
import pl.zetosoftware.car.enums.BodyTypeEnum;
import pl.zetosoftware.car.enums.TypeOfFuelEnum;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CarWebController {

    private final CarFilterService carFilterService;

    @GetMapping(value = "/filter/cars", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<CarDto> getAllCarsWithFilters(@RequestParam(required = false) String brand, @RequestParam(required = false) String model,
                                              @RequestParam(required = false) BigDecimal engineCapacity, @RequestParam(required = false) BodyTypeEnum bodyType,
                                              @RequestParam(required = false) TypeOfFuelEnum typeOfFuel, @RequestParam(required = false) Integer productionYear,
                                              @RequestParam(required = false) String freeFrom, @RequestParam(required = false) String freeTo) {

        var carFilterDto = carFilterService.getCarFilterDtoFromParams(brand, model, engineCapacity, bodyType, typeOfFuel, productionYear, freeFrom, freeTo);
        return carFilterService.getFilteredCars(carFilterDto);
    }

}
