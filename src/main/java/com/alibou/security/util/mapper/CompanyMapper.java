package com.alibou.security.util.mapper;

import com.alibou.security.model.entity.Company;
import com.alibou.security.rest.dto.CompanyDto;
import com.alibou.security.rest.dto.request.CreateCompanyRequest;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CompanyMapper {

    CompanyDto map(Company company);

    Company map(CompanyDto company);

    Company map(CreateCompanyRequest request);

    CompanyDto mapToDto(CreateCompanyRequest request);
}
