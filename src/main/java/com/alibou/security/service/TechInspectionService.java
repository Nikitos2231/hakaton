package com.alibou.security.service;

import com.alibou.security.rest.dto.request.ConditionsRequest;
import com.alibou.security.rest.dto.response.EntitiesTableResultDto;
import com.alibou.security.rest.dto.response.SingleTableResult;
import com.alibou.security.rest.dto.TechInspectionDto;

import java.util.UUID;

public interface TechInspectionService {
    EntitiesTableResultDto<TechInspectionDto> getAll(ConditionsRequest request);
    SingleTableResult<TechInspectionDto> getInspection(UUID inspectionId);
    SingleTableResult<TechInspectionDto> save(TechInspectionDto techInspectionDto);
    SingleTableResult<TechInspectionDto> edit(TechInspectionDto techInspectionDto, UUID inspectionId);
}
