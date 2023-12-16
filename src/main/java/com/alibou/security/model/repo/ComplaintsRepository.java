package com.alibou.security.model.repo;

import com.alibou.security.model.entity.Complaints;
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
public interface ComplaintsRepository extends JpaRepository<Complaints, UUID>, IBaseConditions<Complaints> {
    default EntitiesTableResult<Complaints> getAllWithSortAndFilterAndPaginate(ConditionsRequest condition, EntityManager entityManager, Pageable pageable) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Complaints> query = builder.createQuery(Complaints.class);
        Root<Complaints> root = query.from(Complaints.class);

        Predicate predicate = filter(builder, condition, root);

        predicate = filter(predicate, builder, root);

        order(condition, builder, root, query, "rejectDate");

        query.where(predicate);

        TypedQuery<Complaints> typedQuery = entityManager.createQuery(query);

        PageableResult pageableResult = paginate(typedQuery, pageable);

        return new EntitiesTableResult<>(typedQuery.getResultList(), pageableResult.getTotalPages(), pageableResult.getTotalElements());
    }
}
