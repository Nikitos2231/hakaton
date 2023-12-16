package com.alibou.security.service;

import com.alibou.security.rest.dto.CarDto;
import com.alibou.security.rest.dto.ShortCarDto;
import com.alibou.security.rest.dto.request.ConditionsRequest;
import com.alibou.security.rest.dto.request.EditCarRequest;
import com.alibou.security.rest.dto.response.CarNumbersResponse;
import com.alibou.security.rest.dto.response.EntitiesTableResultDto;
import com.alibou.security.rest.dto.response.SingleTableResult;

public interface CarService {

    EntitiesTableResultDto<CarDto> getAll(ConditionsRequest condition);

    SingleTableResult<CarDto> findBySerialNumber(String serialNumber);

    SingleTableResult<ShortCarDto> findBySerialNumberAnonymize(String serialNumber);

    CarNumbersResponse findBySerialNumberContains(String serialNumberPart);

    CarNumbersResponse findBySerialNumberContainsAnonymize(String serialNumberPart);

    SingleTableResult<CarDto> save(CarDto carDto);

    SingleTableResult<CarDto> edit(EditCarRequest carDto, String serialNumber);

    void delete(String serialNumber);
}
