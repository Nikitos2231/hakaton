package com.alibou.security.model.repo;

import com.alibou.security.model.entity.Car;
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

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, String>, IBaseConditions<Car> {

    Optional<Car> findBySerialNumber(String serialNumber);

    List<Car> findBySerialNumberContains(String serialNumber);

    default EntitiesTableResult<Car> getAllWithSortAndFilterAndPaginate(ConditionsRequest condition, EntityManager entityManager, Pageable pageable) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Car> query = builder.createQuery(Car.class);
        Root<Car> root = query.from(Car.class);

        Predicate predicate = filter(builder, condition, root);

        predicate = filterByCar(predicate, builder, root);

        order(condition, builder, root, query, "factoryDateShipment");

        query.where(predicate);

        TypedQuery<Car> typedQuery = entityManager.createQuery(query);

        PageableResult pageableResult = paginate(typedQuery, pageable);

        return new EntitiesTableResult<>(typedQuery.getResultList(), pageableResult.getTotalPages(), pageableResult.getTotalElements());
    }
}
