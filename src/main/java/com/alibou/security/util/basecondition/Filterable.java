package com.alibou.security.util.basecondition;

import com.alibou.security.rest.dto.request.ConditionsRequest;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.time.LocalDate;
import java.util.Map;

public interface Filterable {

    default Predicate filter(CriteriaBuilder builder, ConditionsRequest condition, Root<?> root) {
        Predicate predicate = builder.conjunction();
        for (Map.Entry<String, String> entry : condition.getFilters().entrySet()) {
            String attributeName = entry.getKey();
            String filterValue = entry.getValue();

            Class<?> attributeType = root.get(attributeName).getJavaType();

            if (attributeType.equals(String.class)) {
                predicate = builder.and(predicate, builder.like(root.get(attributeName), "%" + filterValue + "%"));
            } else if (attributeType.equals(Integer.class)) {
                predicate = builder.and(predicate, builder.equal(root.get(attributeName), Integer.parseInt(filterValue)));
            } else if (attributeType.equals(LocalDate.class)) {
                predicate = builder.and(predicate, builder.equal(root.get(attributeName), LocalDate.parse(filterValue)));
            }
        }
        return predicate;
    }

}
