package com.alibou.security.rest.controller;

import com.alibou.security.rest.dto.ComplaintsDto;
import com.alibou.security.rest.dto.request.ConditionsRequest;
import com.alibou.security.rest.dto.response.EntitiesTableResultDto;
import com.alibou.security.rest.dto.response.ResponseDto;
import com.alibou.security.rest.dto.response.SingleTableResult;
import com.alibou.security.service.ComplaintsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/complaints")
@RequiredArgsConstructor
@CrossOrigin
public class ComplaintsController {

    private final ComplaintsService complaintsService;

    @PostMapping
    @PreAuthorize("hasAnyRole('MANAGER', 'CLIENT', 'SERVICE_ORGANISATION')")
    public ResponseDto<EntitiesTableResultDto<ComplaintsDto>> getInspections(@RequestBody @Valid ConditionsRequest request) {
        return ResponseDto.ok(complaintsService.getAll(request));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('MANAGER', 'CLIENT', 'SERVICE_ORGANISATION')")
    public ResponseDto<SingleTableResult<ComplaintsDto>> getInspections(@PathVariable UUID id) {
        return ResponseDto.ok(complaintsService.getById(id));
    }

    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('MANAGER', 'SERVICE_ORGANISATION')")
    public ResponseDto<SingleTableResult<ComplaintsDto>> save(@RequestBody ComplaintsDto complaintsDto) {
        return ResponseDto.ok(complaintsService.save(complaintsDto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('MANAGER', 'SERVICE_ORGANISATION')")
    public ResponseDto<SingleTableResult<ComplaintsDto>> edit(@RequestBody ComplaintsDto complaintsDto, @PathVariable UUID id) {
        return ResponseDto.ok(complaintsService.edit(complaintsDto, id));
    }
}
