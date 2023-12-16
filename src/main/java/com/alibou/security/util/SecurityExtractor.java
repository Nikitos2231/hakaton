package com.alibou.security.util;

import com.alibou.security.model.entity.User;
import com.alibou.security.model.entity.enums.Role;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityExtractor {

    public static Role getRole() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getRole();
    }

    public static String getCompany() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getCompany();
    }
}
