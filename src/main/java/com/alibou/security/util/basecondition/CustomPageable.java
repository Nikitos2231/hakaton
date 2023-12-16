package com.alibou.security.util.basecondition;

import com.alibou.security.rest.dto.PageableResult;
import jakarta.persistence.TypedQuery;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomPageable<T> {

    default PageableResult paginate(TypedQuery<T> typedQuery, Pageable pageable) {
        List<T> resultList = typedQuery.getResultList();
        Integer totalElements = resultList.size();
        int totalPages = (int) Math.ceil((double) totalElements / pageable.getPageSize());

        typedQuery.setFirstResult((int) pageable.getOffset());
        typedQuery.setMaxResults(pageable.getPageSize());
        return new PageableResult(totalPages, totalElements);
    }
}
