package com.alibou.security.service.impl;

import com.alibou.security.exception.TechInspectionException;
import com.alibou.security.model.entity.TechInspection;
import com.alibou.security.model.repo.TechInspectionRepository;
import com.alibou.security.rest.dto.EntitiesTableResult;
import com.alibou.security.rest.dto.TechInspectionDto;
import com.alibou.security.rest.dto.request.ConditionsRequest;
import com.alibou.security.rest.dto.response.EntitiesTableResultDto;
import com.alibou.security.rest.dto.response.SingleTableResult;
import com.alibou.security.service.TechInspectionService;
import com.alibou.security.util.ClassUtil;
import com.alibou.security.util.mapper.TechInspectionMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TechInspectionServiceImpl implements TechInspectionService {

    @PersistenceContext
    private EntityManager entityManager;
    private final TechInspectionRepository techInspectionRepository;
    private final TechInspectionMapper techInspectionMapper;

    @Override
    public EntitiesTableResultDto<TechInspectionDto> getAll(ConditionsRequest condition) {
        EntitiesTableResult<TechInspection> techInspectionsList = techInspectionRepository.getAllWithSortAndFilterAndPaginate(condition, entityManager,
                Pageable.ofSize(condition.getPage().getAmountOfElements())
                        .withPage(condition.getPage().getPageNumber()));
        return techInspectionMapper.map(techInspectionsList).toBuilder()
                .fields(ClassUtil.getAllFieldNames(TechInspectionDto.class))
                .build();
    }

    @Override
    public SingleTableResult<TechInspectionDto> getInspection(UUID inspectionId) {
        TechInspection techInspection = techInspectionRepository.findById(inspectionId)
                .orElseThrow(() -> TechInspectionException.notFoundByName(inspectionId));
        return new SingleTableResult<>(techInspectionMapper.map(techInspection), ClassUtil.getAllFieldNames(TechInspectionDto.class));
    }

    @Override
    public SingleTableResult<TechInspectionDto> save(TechInspectionDto techInspectionDto) {
        TechInspection techInspection = techInspectionMapper.map(techInspectionDto);
        techInspection.setId(UUID.randomUUID());
        techInspectionRepository.save(techInspection);
        return new SingleTableResult<>(techInspectionMapper.map(techInspection), ClassUtil.getAllFieldNames(TechInspectionDto.class));
    }

    @Override
    public SingleTableResult<TechInspectionDto> edit(TechInspectionDto techInspectionDto, UUID inspectionId) {
        techInspectionDto.setId(techInspectionRepository.findById(inspectionId)
                .orElseThrow(() -> TechInspectionException.notFoundByName(inspectionId)).getId());
        TechInspection techInspection = techInspectionRepository.save(techInspectionMapper.map(techInspectionDto));
        return new SingleTableResult<>(techInspectionMapper.map(techInspection), ClassUtil.getAllFieldNames(TechInspectionDto.class));
    }
}
