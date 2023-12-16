package com.alibou.security.service.impl;

import com.alibou.security.exception.CompanyException;
import com.alibou.security.model.entity.Company;
import com.alibou.security.model.repo.CompanyRepository;
import com.alibou.security.rest.dto.CompanyDto;
import com.alibou.security.rest.dto.request.CreateCompanyRequest;
import com.alibou.security.rest.dto.response.CompanyNamesResponse;
import com.alibou.security.service.CompanyService;
import com.alibou.security.util.mapper.CompanyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final String excludedCompanyName = "самостоятельно";

    @Override
    public List<CompanyDto> getAll() {
        return companyRepository.findAll()
                .stream()
                .filter(company -> !excludedCompanyName.equals(company.getName()))
                .map(companyMapper::map)
                .toList();
    }

    @Override
    public CompanyNamesResponse findByNamePart(String namePart) {
        return new CompanyNamesResponse(companyRepository.findByNameContains(namePart)
                .stream()
                .map(Company::getName)
                .toList());
    }

    @Override
    public Optional<Company> findByNameOpt(String name) {
        return companyRepository.findByName(name);
    }

    @Override
    public CompanyDto findByName(String name) {
        return companyRepository.findByName(name)
                .map(companyMapper::map)
                .orElseThrow(() -> CompanyException.notFoundByName(name));
    }

    @Override
    public CompanyDto saveCompany(CreateCompanyRequest request) {
        if (findByNameOpt(request.name()).isPresent()) {
            throw CompanyException.alreadyExists(request.name());
        }
        return companyMapper.map(companyRepository.save(companyMapper.map(request)));
    }

    @Override
    public CompanyDto editCompany(CreateCompanyRequest request, String name) {
        if (findByNameOpt(name).isEmpty()) {
            throw CompanyException.notFoundByName(name);
        }
        if (!name.equals(request.name()) && findByNameOpt(request.name()).isPresent()) {
            throw CompanyException.alreadyExists(request.name());
        }
        companyRepository.edit(name, companyMapper.map(request));
        return companyMapper.mapToDto(request);
    }

    @Override
    public void delete(String name) {
        if (findByNameOpt(name).isEmpty()) {
            throw CompanyException.notFoundByName(name);
        }
        companyRepository.deleteById(name);
    }
}
