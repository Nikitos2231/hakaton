package com.alibou.security.rest.controller;

import com.alibou.security.rest.dto.DirectoryDto;
import com.alibou.security.rest.dto.DirectoryValues;
import com.alibou.security.rest.dto.request.AddDirectoryRequest;
import com.alibou.security.rest.dto.request.DirectoryTypesRequest;
import com.alibou.security.rest.dto.response.ResponseDto;
import com.alibou.security.service.DirectoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/directories")
@RequiredArgsConstructor
@CrossOrigin
public class DirectoryController {

    private final DirectoryService directoryService;

    @PostMapping("/values")
    @PreAuthorize("hasAnyRole('MANAGER', 'CLIENT', 'SERVICE_ORGANISATION')")
    public ResponseDto<DirectoryValues> getDirectoryValuesByType(@RequestBody @Valid DirectoryTypesRequest request) {
        return ResponseDto.ok(directoryService.getDirectoryValuesByType(request));
    }

    @PostMapping
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseDto<DirectoryDto> saveDirectory(@RequestBody @Valid AddDirectoryRequest request) {
        return ResponseDto.ok(directoryService.save(request));
    }

    @PutMapping("/{name}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseDto<DirectoryDto> editDirectory(@RequestBody @Valid AddDirectoryRequest request,
                                                   @PathVariable String name) {
        return ResponseDto.ok(directoryService.edit(request, name));
    }
}
