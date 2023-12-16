package com.alibou.security.service.impl;

import com.alibou.security.exception.CarException;
import com.alibou.security.model.entity.Car;
import com.alibou.security.model.repo.CarRepository;
import com.alibou.security.rest.dto.CarDto;
import com.alibou.security.rest.dto.EntitiesTableResult;
import com.alibou.security.rest.dto.Page;
import com.alibou.security.rest.dto.ShortCarDto;
import com.alibou.security.rest.dto.request.ConditionsRequest;
import com.alibou.security.rest.dto.request.EditCarRequest;
import com.alibou.security.rest.dto.response.CarNumbersResponse;
import com.alibou.security.rest.dto.response.EntitiesTableResultDto;
import com.alibou.security.rest.dto.response.SingleTableResult;
import com.alibou.security.service.CarService;
import com.alibou.security.util.ClassUtil;
import com.alibou.security.util.mapper.CarMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public EntitiesTableResultDto<CarDto> getAll(ConditionsRequest condition) {
        EntitiesTableResult<Car> cars = carRepository.getAllWithSortAndFilterAndPaginate(condition, entityManager,
                Pageable.ofSize(condition.getPage().getAmountOfElements())
                        .withPage(condition.getPage().getPageNumber()));
        return carMapper.map(cars).toBuilder()
                .fields(ClassUtil.getAllFieldNames(CarDto.class))
                .build();
    }

    @Override
    public SingleTableResult<ShortCarDto> findBySerialNumberAnonymize(String serialNumber) {
        return SingleTableResult.<ShortCarDto>builder()
                .entity(carRepository.findBySerialNumber(serialNumber)
                        .map(carMapper::mapToShortCar)
                        .orElseThrow(() -> CarException.notFound(serialNumber)))
                .fields(ClassUtil.getAllFieldNames(ShortCarDto.class))
                .build();
    }

    @Override
    public CarNumbersResponse findBySerialNumberContainsAnonymize(String serialNumberPart) {
        return CarNumbersResponse.builder()
                .numbers(getCarsNumbersBySerialNumber(serialNumberPart))
                .build();
    }

    @Override
    public SingleTableResult<CarDto> save(CarDto carDto) {
        if (carRepository.findBySerialNumber(carDto.getSerialNumber()).isPresent()) {
            throw CarException.alreadyExists(carDto.getSerialNumber());
        }
        Car car = carRepository.save(carMapper.map(carDto));
        return SingleTableResult.<CarDto>builder()
                .entity(carMapper.map(car))
                .fields(ClassUtil.getAllFieldNames(CarDto.class))
                .build();
    }

    @Override
    public SingleTableResult<CarDto> edit(EditCarRequest editCar, String serialNumber) {
        if (carRepository.findBySerialNumber(serialNumber).isEmpty()) {
            throw CarException.notFound(serialNumber);
        }
        editCar.setSerialNumber(serialNumber);
        carRepository.save(carMapper.map(editCar));
        return SingleTableResult.<CarDto>builder()
                .entity(carMapper.mapToCarDto(editCar))
                .fields(ClassUtil.getAllFieldNames(CarDto.class))
                .build();
    }

    @Override
    public void delete(String serialNumber) {
        if (carRepository.findBySerialNumber(serialNumber).isEmpty()) {
            throw CarException.notFound(serialNumber);
        }
        carRepository.deleteById(serialNumber);
    }

    @Override
    public CarNumbersResponse findBySerialNumberContains(String serialNumberPart) {
        return CarNumbersResponse.builder()
                .numbers(generateEmptyConditionResult().getEntities()
                        .stream()
                        .map(CarDto::getSerialNumber)
                        .filter(serialNumber -> serialNumber.contains(serialNumberPart))
                        .toList())
                .build();
    }

    @Override
    public SingleTableResult<CarDto> findBySerialNumber(String serialNumber) {
        return SingleTableResult.<CarDto>builder()
                .entity(generateEmptyConditionResult().getEntities().stream()
                        .filter(car -> car.getSerialNumber().equals(serialNumber))
                        .findFirst()
                        .orElseThrow(() -> CarException.notFound(serialNumber)))
                .fields(ClassUtil.getAllFieldNames(CarDto.class))
                .build();
    }

    private EntitiesTableResultDto<CarDto> generateEmptyConditionResult() {
        return getAll(ConditionsRequest.builder()
                .filters(new HashMap<>())
                .page(new Page())
                .sorting(new HashMap<>())
                .build());
    }

    private List<String> getCarsNumbersBySerialNumber(String serialNumberPart) {
        return carRepository.findBySerialNumberContains(serialNumberPart)
                .stream()
                .map(Car::getSerialNumber)
                .toList();
    }
}
