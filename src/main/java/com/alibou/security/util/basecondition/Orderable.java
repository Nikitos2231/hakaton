package com.alibou.security.util.basecondition;

import com.alibou.security.rest.dto.request.ConditionsRequest;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Root;

import java.util.List;

public interface Orderable {

    default void order(ConditionsRequest condition, CriteriaBuilder builder, Root<?> root, CriteriaQuery query, String defaultField) {
        if (!condition.getSorting().isEmpty()) {
            List<Order> orders = condition.getSorting().entrySet().stream()
                    .map(entry -> {
                        if ("asc".equals(entry.getValue())) {
                            return builder.asc(root.get(entry.getKey()));
                        }
                        return builder.desc(root.get(entry.getKey()));
                    })
                    .toList();
            query.orderBy(orders);
        } else {
            query.orderBy(builder.asc(root.get(defaultField)));
        }
    }
}
