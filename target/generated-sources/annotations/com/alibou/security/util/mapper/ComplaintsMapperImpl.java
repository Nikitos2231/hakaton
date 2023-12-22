package com.alibou.security.util.mapper;

import com.alibou.security.model.entity.Complaints;
import com.alibou.security.rest.dto.ComplaintsDto;
import com.alibou.security.rest.dto.EntitiesTableResult;
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
public class ComplaintsMapperImpl implements ComplaintsMapper {

    @Override
    public EntitiesTableResultDto<ComplaintsDto> map(EntitiesTableResult<Complaints> techInspectionsList) {
        if ( techInspectionsList == null ) {
            return null;
        }

        EntitiesTableResultDto<ComplaintsDto> entitiesTableResultDto = new EntitiesTableResultDto<ComplaintsDto>();

        entitiesTableResultDto.setEntities( complaintsListToComplaintsDtoList( techInspectionsList.getEntities() ) );
        entitiesTableResultDto.setTotalPages( techInspectionsList.getTotalPages() );
        entitiesTableResultDto.setTotalElements( techInspectionsList.getTotalElements() );

        return entitiesTableResultDto;
    }

    @Override
    public ComplaintsDto map(Complaints complaints) {
        if ( complaints == null ) {
            return null;
        }

        ComplaintsDto complaintsDto = new ComplaintsDto();

        complaintsDto.setId( complaints.getId() );
        complaintsDto.setCarId( complaints.getCarId() );
        complaintsDto.setRejectDate( complaints.getRejectDate() );
        complaintsDto.setOperating( complaints.getOperating() );
        complaintsDto.setRejectNode( complaints.getRejectNode() );
        complaintsDto.setRejectDescription( complaints.getRejectDescription() );
        complaintsDto.setRecoveryMethod( complaints.getRecoveryMethod() );
        complaintsDto.setSparePartsUsed( complaints.getSparePartsUsed() );
        complaintsDto.setRecoveryDate( complaints.getRecoveryDate() );
        complaintsDto.setEquipmentDowntime( complaints.getEquipmentDowntime() );
        complaintsDto.setCompany( complaints.getCompany() );

        return complaintsDto;
    }

    @Override
    public Complaints map(ComplaintsDto complaints) {
        if ( complaints == null ) {
            return null;
        }

        Complaints.ComplaintsBuilder complaints1 = Complaints.builder();

        complaints1.id( complaints.getId() );
        complaints1.carId( complaints.getCarId() );
        complaints1.rejectDate( complaints.getRejectDate() );
        complaints1.operating( complaints.getOperating() );
        complaints1.rejectNode( complaints.getRejectNode() );
        complaints1.rejectDescription( complaints.getRejectDescription() );
        complaints1.recoveryMethod( complaints.getRecoveryMethod() );
        complaints1.sparePartsUsed( complaints.getSparePartsUsed() );
        complaints1.recoveryDate( complaints.getRecoveryDate() );
        complaints1.equipmentDowntime( complaints.getEquipmentDowntime() );
        complaints1.company( complaints.getCompany() );

        return complaints1.build();
    }

    protected List<ComplaintsDto> complaintsListToComplaintsDtoList(List<Complaints> list) {
        if ( list == null ) {
            return null;
        }

        List<ComplaintsDto> list1 = new ArrayList<ComplaintsDto>( list.size() );
        for ( Complaints complaints : list ) {
            list1.add( map( complaints ) );
        }

        return list1;
    }
}
