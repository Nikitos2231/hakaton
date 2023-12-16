package com.alibou.security.model.repo;

import com.alibou.security.model.entity.TechInspection;
import com.alibou.security.rest.dto.EntitiesTableResult;
import com.alibou.security.rest.dto.PageableResult;
import com.alibou.security.rest.dto.request.ConditionsRequest;
import com.alibou.security.util.basecondition.IBaseConditions;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TechInspectionRepository extends JpaRepository<TechInspection, UUID>, IBaseConditions<TechInspection> {

    default EntitiesTableResult<TechInspection> getAllWithSortAndFilterAndPaginate(ConditionsRequest condition, EntityManager entityManager, Pageable pageable) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<TechInspection> query = builder.createQuery(TechInspection.class);
        Root<TechInspection> root = query.from(TechInspection.class);

        Predicate predicate = filter(builder, condition, root);

        predicate = filter(predicate, builder, root);

        order(condition, builder, root, query, "maintenanceDate");

        query.where(predicate);

        TypedQuery<TechInspection> typedQuery = entityManager.createQuery(query);

        PageableResult pageableResult = paginate(typedQuery, pageable);

        return new EntitiesTableResult<>(typedQuery.getResultList(), pageableResult.getTotalPages(), pageableResult.getTotalElements());
    }
}
