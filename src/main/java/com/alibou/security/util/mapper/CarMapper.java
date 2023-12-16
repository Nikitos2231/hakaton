package com.alibou.security.util.mapper;

import com.alibou.security.model.entity.Car;
import com.alibou.security.rest.dto.CarDto;
import com.alibou.security.rest.dto.EntitiesTableResult;
import com.alibou.security.rest.dto.ShortCarDto;
import com.alibou.security.rest.dto.request.EditCarRequest;
import com.alibou.security.rest.dto.response.EntitiesTableResultDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CarMapper {

    CarDto map(Car car);
    Car map(EditCarRequest car);
    CarDto mapToCarDto(EditCarRequest car);

    ShortCarDto mapToShortCar(Car car);

    EntitiesTableResultDto<CarDto> map(EntitiesTableResult<Car> techInspectionsList);

    Car map(CarDto car);
}
