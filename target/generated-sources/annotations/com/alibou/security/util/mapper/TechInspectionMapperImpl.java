package com.alibou.security.util.mapper;

import com.alibou.security.model.entity.TechInspection;
import com.alibou.security.rest.dto.EntitiesTableResult;
import com.alibou.security.rest.dto.TechInspectionDto;
import com.alibou.security.rest.dto.response.EntitiesTableResultDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-22T23:46:19+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Eclipse Adoptium)"
)
@Component
public class TechInspectionMapperImpl implements TechInspectionMapper {

    @Override
    public EntitiesTableResultDto<TechInspectionDto> map(EntitiesTableResult<TechInspection> techInspectionsList) {
        if ( techInspectionsList == null ) {
            return null;
        }

        EntitiesTableResultDto<TechInspectionDto> entitiesTableResultDto = new EntitiesTableResultDto<TechInspectionDto>();

        entitiesTableResultDto.setEntities( techInspectionListToTechInspectionDtoList( techInspectionsList.getEntities() ) );
        entitiesTableResultDto.setTotalPages( techInspectionsList.getTotalPages() );
        entitiesTableResultDto.setTotalElements( techInspectionsList.getTotalElements() );

        return entitiesTableResultDto;
    }

    @Override
    public TechInspectionDto map(TechInspection techInspectionsList) {
        if ( techInspectionsList == null ) {
            return null;
        }

        TechInspectionDto techInspectionDto = new TechInspectionDto();

        techInspectionDto.setId( techInspectionsList.getId() );
        techInspectionDto.setCarId( techInspectionsList.getCarId() );
        techInspectionDto.setType( techInspectionsList.getType() );
        techInspectionDto.setMaintenanceDate( techInspectionsList.getMaintenanceDate() );
        techInspectionDto.setOperating( techInspectionsList.getOperating() );
        techInspectionDto.setOrderNumber( techInspectionsList.getOrderNumber() );
        techInspectionDto.setOrderNumberDate( techInspectionsList.getOrderNumberDate() );
        techInspectionDto.setCompany( techInspectionsList.getCompany() );

        return techInspectionDto;
    }

    @Override
    public TechInspection map(TechInspectionDto techInspectionsList) {
        if ( techInspectionsList == null ) {
            return null;
        }

        TechInspection.TechInspectionBuilder techInspection = TechInspection.builder();

        techInspection.id( techInspectionsList.getId() );
        techInspection.carId( techInspectionsList.getCarId() );
        techInspection.type( techInspectionsList.getType() );
        techInspection.maintenanceDate( techInspectionsList.getMaintenanceDate() );
        techInspection.operating( techInspectionsList.getOperating() );
        techInspection.orderNumber( techInspectionsList.getOrderNumber() );
        techInspection.orderNumberDate( techInspectionsList.getOrderNumberDate() );
        techInspection.company( techInspectionsList.getCompany() );

        return techInspection.build();
    }

    protected List<TechInspectionDto> techInspectionListToTechInspectionDtoList(List<TechInspection> list) {
        if ( list == null ) {
            return null;
        }

        List<TechInspectionDto> list1 = new ArrayList<TechInspectionDto>( list.size() );
        for ( TechInspection techInspection : list ) {
            list1.add( map( techInspection ) );
        }

        return list1;
    }
}
