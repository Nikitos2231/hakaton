package com.alibou.security.util.mapper;

import com.alibou.security.model.entity.Complaints;
import com.alibou.security.rest.dto.ComplaintsDto;
import com.alibou.security.rest.dto.EntitiesTableResult;
import com.alibou.security.rest.dto.response.EntitiesTableResultDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ComplaintsMapper {
    EntitiesTableResultDto<ComplaintsDto> map(EntitiesTableResult<Complaints> techInspectionsList);

    ComplaintsDto map(Complaints complaints);

    Complaints map(ComplaintsDto complaints);
}
