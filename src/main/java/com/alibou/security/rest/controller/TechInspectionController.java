package com.alibou.security.rest.controller;

import com.alibou.security.rest.dto.TechInspectionDto;
import com.alibou.security.rest.dto.request.ConditionsRequest;
import com.alibou.security.rest.dto.response.EntitiesTableResultDto;
import com.alibou.security.rest.dto.response.ResponseDto;
import com.alibou.security.rest.dto.response.SingleTableResult;
import com.alibou.security.service.TechInspectionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/inspections")
@RequiredArgsConstructor
@CrossOrigin
public class TechInspectionController {
    private final TechInspectionService techInspectionService;

    @PostMapping
    @PreAuthorize("hasAnyRole('MANAGER', 'CLIENT', 'SERVICE_ORGANISATION')")
    public ResponseDto<EntitiesTableResultDto<TechInspectionDto>> getInspections(@RequestBody @Valid ConditionsRequest request) {
        return ResponseDto.ok(techInspectionService.getAll(request));
    }

    @GetMapping("/{inspectionId}")
    @PreAuthorize("hasAnyRole('MANAGER', 'CLIENT', 'SERVICE_ORGANISATION')")
    public ResponseDto<SingleTableResult<TechInspectionDto>> getInspection(@PathVariable UUID inspectionId) {
        return ResponseDto.ok(techInspectionService.getInspection(inspectionId));
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('MANAGER', 'CLIENT', 'SERVICE_ORGANISATION')")
    public ResponseDto<SingleTableResult<TechInspectionDto>> save(@RequestBody @Valid TechInspectionDto techInspectionDto) {
        return ResponseDto.ok(techInspectionService.save(techInspectionDto));
    }

    @PutMapping("/{inspectionId}")
    @PreAuthorize("hasAnyRole('MANAGER', 'CLIENT', 'SERVICE_ORGANISATION')")
    public ResponseDto<SingleTableResult<TechInspectionDto>> edit(@RequestBody @Valid TechInspectionDto techInspectionDto,
                                                                  @PathVariable UUID inspectionId) {
        return ResponseDto.ok(techInspectionService.edit(techInspectionDto, inspectionId));
    }
}
