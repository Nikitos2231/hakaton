package com.alibou.security.util.mapper;

import com.alibou.security.model.entity.Company;
import com.alibou.security.rest.dto.CompanyDto;
import com.alibou.security.rest.dto.request.CreateCompanyRequest;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-16T15:07:24+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Eclipse Adoptium)"
)
@Component
public class CompanyMapperImpl implements CompanyMapper {

    @Override
    public CompanyDto map(Company company) {
        if ( company == null ) {
            return null;
        }

        String name = null;
        String description = null;

        name = company.getName();
        description = company.getDescription();

        CompanyDto companyDto = new CompanyDto( name, description );

        return companyDto;
    }

    @Override
    public Company map(CompanyDto company) {
        if ( company == null ) {
            return null;
        }

        Company.CompanyBuilder company1 = Company.builder();

        company1.name( company.name() );
        company1.description( company.description() );

        return company1.build();
    }

    @Override
    public Company map(CreateCompanyRequest request) {
        if ( request == null ) {
            return null;
        }

        Company.CompanyBuilder company = Company.builder();

        company.name( request.name() );
        company.description( request.description() );

        return company.build();
    }

    @Override
    public CompanyDto mapToDto(CreateCompanyRequest request) {
        if ( request == null ) {
            return null;
        }

        String name = null;
        String description = null;

        name = request.name();
        description = request.description();

        CompanyDto companyDto = new CompanyDto( name, description );

        return companyDto;
    }
}
