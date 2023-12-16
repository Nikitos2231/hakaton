package com.alibou.security.util.mapper;

import com.alibou.security.model.entity.TechInspection;
import com.alibou.security.rest.dto.EntitiesTableResult;
import com.alibou.security.rest.dto.TechInspectionDto;
import com.alibou.security.rest.dto.response.EntitiesTableResultDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TechInspectionMapper {

    EntitiesTableResultDto<TechInspectionDto> map(EntitiesTableResult<TechInspection> techInspectionsList);

    TechInspectionDto map(TechInspection techInspectionsList);

    TechInspection map(TechInspectionDto techInspectionsList);
}
