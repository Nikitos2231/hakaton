package com.alibou.security.rest.dto.request;

import com.alibou.security.rest.dto.Page;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConditionsRequest {
    @NotNull(message = "Filters couldn't be null")
    private Map<String, String> filters;
    @NotNull(message = "Sorting couldn't be null")
    private Map<String, String> sorting;
    @Valid
    private Page page = new Page();
}
