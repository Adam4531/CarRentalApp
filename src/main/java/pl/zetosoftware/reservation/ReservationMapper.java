package pl.zetosoftware.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.zetosoftware.car.CarEntity;
import pl.zetosoftware.car.CarService;
import pl.zetosoftware.car.dto.CarDto;
import pl.zetosoftware.reservation.dto.ReservationCarDto;
import pl.zetosoftware.reservation.dto.ReservationDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReservationMapper {

    @Autowired
    public CarService carService;

    public ReservationDto fromReservationToReservationDto(ReservationEntity reservationEntity){
        return ReservationDto.builder()
                .userId(reservationEntity.getUserId().getId())
                .carId(reservationEntity.getCarId().getId())
                .dateStart(reservationEntity.getDate().dateStart)
                .dateEnd(reservationEntity.getDate().dateEnd)
                .cost(reservationEntity.getCost().getCost())
                .paymentInAdvance(reservationEntity.getPaymentInAdvance().getPaymentInAdvance())
                .build();
    }

    public List<ReservationDto> fromReservationListToReservationDtoList(List<ReservationEntity> reservationEntityList){
        return reservationEntityList.stream()
                .map(this::fromReservationToReservationDto)
                .collect(Collectors.toList());
    }

    public ReservationCarDto fromCarDtoToReservationCarDto(CarEntity carEntity){
        return ReservationCarDto.builder()
                .brand(carEntity.getBrand().toString())
                .model(carEntity.getModel().toString())
                .engineCapacity(carEntity.getEngineCapacity().toBigDecimal())
                .bodyTypeEnum(carEntity.getBodyType())
                .status(carEntity.getStatus().toString())
                .build();
    }

    public List<ReservationCarDto> fromCarEntityListToReservationCarDtoList(List<CarEntity> carEntityList){
        return carEntityList.stream()
                .map(this::fromCarDtoToReservationCarDto)
                .collect(Collectors.toList());
    }

    public ReservationCarDto fromCarDtoToReservationCarDto(CarDto carDto){
        return ReservationCarDto.builder()
                .brand(carDto.brand())
                .model(carDto.model())
                .engineCapacity(carDto.engineCapacity())
                .bodyTypeEnum(carDto.bodyTypeEnum())
                .status(carDto.statusEnum().toString())
                .build();
    }

    public List<ReservationCarDto> fromCarDtoListToReservationCarDtoList(List<CarDto> carDtoList){
        return carDtoList.stream()
                .map(this::fromCarDtoToReservationCarDto)
                .collect(Collectors.toList());
    }
}
