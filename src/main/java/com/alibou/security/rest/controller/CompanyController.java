package com.alibou.security.rest.controller;

import com.alibou.security.rest.dto.CompanyDto;
import com.alibou.security.rest.dto.request.CreateCompanyRequest;
import com.alibou.security.rest.dto.response.CompanyNamesResponse;
import com.alibou.security.rest.dto.response.ResponseDto;
import com.alibou.security.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/companies")
@RequiredArgsConstructor
@CrossOrigin
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping
    public ResponseDto<List<CompanyDto>> getCompanies() {
        return ResponseDto.ok(companyService.getAll());
    }

    @GetMapping("/{name}")
    public ResponseDto<CompanyDto> getByName(@PathVariable String name) {
        return ResponseDto.ok(companyService.findByName(name));
    }

    @GetMapping("/by-part/{namePart}")
    public ResponseDto<CompanyNamesResponse> getCompaniesNames(@PathVariable String namePart) {
        return ResponseDto.ok(companyService.findByNamePart(namePart));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseDto<CompanyDto> addCompany(@RequestBody @Valid CreateCompanyRequest request) {
        return ResponseDto.ok(companyService.saveCompany(request));
    }

    @PutMapping("/{name}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseDto<CompanyDto> editCompany(@RequestBody @Valid CreateCompanyRequest request, @PathVariable String name) {
        return ResponseDto.ok(companyService.editCompany(request, name));
    }

    @DeleteMapping("/{name}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseDto<?> editCompany(@PathVariable String name) {
        companyService.delete(name);
        return ResponseDto.ok();
    }
}
