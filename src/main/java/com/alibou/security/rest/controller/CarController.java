package com.alibou.security.rest.controller;

import com.alibou.security.rest.dto.CarDto;
import com.alibou.security.rest.dto.DirectoryDto;
import com.alibou.security.rest.dto.ShortCarDto;
import com.alibou.security.rest.dto.request.ConditionsRequest;
import com.alibou.security.rest.dto.request.EditCarRequest;
import com.alibou.security.rest.dto.response.CarNumbersResponse;
import com.alibou.security.rest.dto.response.EntitiesTableResultDto;
import com.alibou.security.rest.dto.response.ResponseDto;
import com.alibou.security.rest.dto.response.SingleTableResult;
import com.alibou.security.service.CarService;
import com.alibou.security.service.DirectoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cars")
@RequiredArgsConstructor
@CrossOrigin
public class CarController {

    private final CarService carService;
    private final DirectoryService directoryService;

    @PostMapping
    @PreAuthorize("hasAnyRole('MANAGER', 'CLIENT', 'SERVICE_ORGANISATION')")
    public ResponseDto<EntitiesTableResultDto<CarDto>> getCars(@RequestBody @Valid ConditionsRequest condition) {
        return ResponseDto.ok(carService.getAll(condition));
    }

    @GetMapping("/list-by-part-anonymize/{serialNumberPart}")
    public ResponseDto<CarNumbersResponse> getCarsByPartAnonymize(@PathVariable String serialNumberPart) {
        return ResponseDto.ok(carService.findBySerialNumberContainsAnonymize(serialNumberPart));
    }

    @GetMapping("/list-by-part/{serialNumberPart}")
    @PreAuthorize("hasAnyRole('MANAGER', 'CLIENT', 'SERVICE_ORGANISATION')")
    public ResponseDto<CarNumbersResponse> getCarsByPart(@PathVariable String serialNumberPart) {
        return ResponseDto.ok(carService.findBySerialNumberContains(serialNumberPart));
    }

    @GetMapping("/serial-number/{serialNumber}")
    public ResponseDto<SingleTableResult<CarDto>> getCarsBySerialNumber(@PathVariable String serialNumber) {
        return ResponseDto.ok(carService.findBySerialNumber(serialNumber));
    }

    @GetMapping("/serial-number-anonymize/{serialNumber}")
    public ResponseDto<SingleTableResult<ShortCarDto>> getCarsBySerialNumberAnonymize(@PathVariable String serialNumber) {
        return ResponseDto.ok(carService.findBySerialNumberAnonymize(serialNumber));
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseDto<SingleTableResult<CarDto>> save(@RequestBody @Valid CarDto carDto) {
        return ResponseDto.ok(carService.save(carDto));
    }

    @PutMapping("/{serialNumber}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseDto<SingleTableResult<CarDto>> edit(@RequestBody @Valid EditCarRequest carDto, @PathVariable String serialNumber) {
        return ResponseDto.ok(carService.edit(carDto, serialNumber));
    }

    @DeleteMapping("/{serialNumber}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseDto<?> delete(@PathVariable String serialNumber) {
        carService.delete(serialNumber);
        return ResponseDto.ok();
    }

    @GetMapping("/directory/{name}")
    public ResponseDto<DirectoryDto> getDirectory(@PathVariable String name) {
        return ResponseDto.ok(directoryService.getByName(name));
    }
}
