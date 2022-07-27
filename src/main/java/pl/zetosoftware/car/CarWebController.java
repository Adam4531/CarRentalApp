package pl.zetosoftware.car;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.zetosoftware.car.dto.CarFilterDto;
import pl.zetosoftware.car.dto.CarDto;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CarWebController {

    private final CarFilterService carFilterService;

    @GetMapping(value = "/filter/cars", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<CarDto> getAllCarsWithFilters(@RequestBody CarFilterDto carFilterDto){
        return carFilterService.getFilteredCars(carFilterDto);
    }

}
