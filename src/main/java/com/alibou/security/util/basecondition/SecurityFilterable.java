package com.alibou.security.util.basecondition;

import com.alibou.security.model.entity.enums.Role;
import com.alibou.security.util.SecurityExtractor;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public interface SecurityFilterable {

    default Predicate filter(Predicate predicate, CriteriaBuilder builder, Root<?> root) {
        Role role = SecurityExtractor.getRole();
        String company = SecurityExtractor.getCompany();
        if (role == Role.CLIENT) {
            predicate = builder.and(predicate,
                    builder.equal(root.join("car").get("client"), company));
        } else if (role == Role.SERVICE_ORGANISATION) {
            predicate = builder.and(predicate,
                    builder.equal(root.get("company"), company));
        }
        return predicate;
    }

    default Predicate filterByCar(Predicate predicate, CriteriaBuilder builder, Root<?> root) {
        Role role = SecurityExtractor.getRole();
        String company = SecurityExtractor.getCompany();
        if (Role.CLIENT == role) {
            predicate = builder.and(predicate,
                    builder.equal(root.get("client"), company));
        } else if (Role.SERVICE_ORGANISATION == role) {
            predicate = builder.and(predicate,
                    builder.equal(root.get("company"), company));
        }
        return predicate;
    }
}
