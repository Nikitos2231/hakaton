package com.alibou.security.service.impl;

import com.alibou.security.exception.ComplaintsException;
import com.alibou.security.model.entity.Complaints;
import com.alibou.security.model.repo.ComplaintsRepository;
import com.alibou.security.rest.dto.ComplaintsDto;
import com.alibou.security.rest.dto.EntitiesTableResult;
import com.alibou.security.rest.dto.request.ConditionsRequest;
import com.alibou.security.rest.dto.response.EntitiesTableResultDto;
import com.alibou.security.rest.dto.response.SingleTableResult;
import com.alibou.security.service.ComplaintsService;
import com.alibou.security.util.ClassUtil;
import com.alibou.security.util.mapper.ComplaintsMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ComplaintsServiceImpl implements ComplaintsService {
    @PersistenceContext
    private EntityManager entityManager;
    private final ComplaintsRepository complaintsRepository;
    private final ComplaintsMapper complaintsMapper;


    @Override
    public EntitiesTableResultDto<ComplaintsDto> getAll(ConditionsRequest condition) {
        EntitiesTableResult<Complaints> complaintsList = complaintsRepository.getAllWithSortAndFilterAndPaginate(condition, entityManager,
                Pageable.ofSize(condition.getPage().getAmountOfElements())
                        .withPage(condition.getPage().getPageNumber()));
        return complaintsMapper.map(complaintsList).toBuilder()
                .fields(ClassUtil.getAllFieldNames(ComplaintsDto.class))
                .build();
    }

    @Override
    public SingleTableResult<ComplaintsDto> getById(UUID id) {
        Complaints complaints = complaintsRepository.findById(id)
                .orElseThrow(() -> ComplaintsException.notFoundByName(id));
        return new SingleTableResult<>(complaintsMapper.map(complaints),
                ClassUtil.getAllFieldNames(ComplaintsDto.class));
    }

    @Override
    public SingleTableResult<ComplaintsDto> save(ComplaintsDto complaintsDto) {
        complaintsDto.setId(UUID.randomUUID());
        Complaints complaints = complaintsRepository.save(complaintsMapper.map(complaintsDto));
        return new SingleTableResult<>(complaintsMapper.map(complaints),
                ClassUtil.getAllFieldNames(ComplaintsDto.class));
    }

    @Override
    public SingleTableResult<ComplaintsDto> edit(ComplaintsDto complaintsDto, UUID id) {
        complaintsDto.setId(complaintsRepository.findById(id)
                .orElseThrow(() -> ComplaintsException.notFoundByName(id)).getId());
        Complaints complaints = complaintsRepository.save(complaintsMapper.map(complaintsDto));
        return new SingleTableResult<>(complaintsMapper.map(complaints),
                ClassUtil.getAllFieldNames(ComplaintsDto.class));
    }
}
