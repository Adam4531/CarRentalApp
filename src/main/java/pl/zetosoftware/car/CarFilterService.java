package pl.zetosoftware.car;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zetosoftware.car.dto.CarFilterDto;
import pl.zetosoftware.car.dto.CarDto;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CarFilterService {

    private final CarRepository carRepository;
    private final CarService carService;
    public List<CarDto> getCars(CarFilterDto carFilterDto) {
        return carService.getAllCars();
    }

}
