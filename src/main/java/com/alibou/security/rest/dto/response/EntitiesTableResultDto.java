package com.alibou.security.rest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
public class EntitiesTableResultDto<T> {

    private List<T> entities;
    private Integer totalPages;
    private Integer totalElements;
    private List<String> fields;
}

