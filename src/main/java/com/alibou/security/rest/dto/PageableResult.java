package com.alibou.security.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageableResult {
    private Integer totalPages;
    private Integer totalElements;
}
