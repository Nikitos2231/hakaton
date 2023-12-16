package com.alibou.security.service;

import com.alibou.security.model.entity.Company;
import com.alibou.security.rest.dto.CompanyDto;
import com.alibou.security.rest.dto.request.CreateCompanyRequest;
import com.alibou.security.rest.dto.response.CompanyNamesResponse;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    List<CompanyDto> getAll();

    CompanyNamesResponse findByNamePart(String namePart);

    Optional<Company> findByNameOpt(String name);

    CompanyDto findByName(String name);

    CompanyDto saveCompany(CreateCompanyRequest request);

    CompanyDto editCompany(CreateCompanyRequest request, String name);

    void delete(String name);
}
