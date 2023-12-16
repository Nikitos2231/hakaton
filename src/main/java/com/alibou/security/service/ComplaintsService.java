package com.alibou.security.service;

import com.alibou.security.rest.dto.ComplaintsDto;
import com.alibou.security.rest.dto.request.ConditionsRequest;
import com.alibou.security.rest.dto.response.EntitiesTableResultDto;
import com.alibou.security.rest.dto.response.SingleTableResult;

import java.util.UUID;

public interface ComplaintsService {
    EntitiesTableResultDto<ComplaintsDto> getAll(ConditionsRequest request);

    SingleTableResult<ComplaintsDto> getById(UUID id);

    SingleTableResult<ComplaintsDto> save(ComplaintsDto complaintsDto);

    SingleTableResult<ComplaintsDto> edit(ComplaintsDto complaintsDto, UUID id);
}
