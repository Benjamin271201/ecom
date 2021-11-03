package com.nashtech.ecommerce.security;

import com.nashtech.ecommerce.auth.ApplicationUser;
import com.nashtech.ecommerce.exception.ForbiddenException;
import com.nashtech.ecommerce.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    //check if this customer id is the same as the logged in one
    public static void isForbidden(int requestAccountId) {
        if (getCurrentUserLoginId() != requestAccountId)
            throw new ForbiddenException();
    }

    public static Integer getCurrentUserLoginId() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return extractPrincipal(securityContext.getAuthentication());
    }
    private static Integer extractPrincipal(Authentication authentication) {
        if (authentication == null) {
            return null;
        } else if (authentication.getPrincipal() instanceof ApplicationUser springSecurityUser) {
            return springSecurityUser.getId();
        } else if (authentication.getPrincipal() instanceof Integer) {
            return (Integer) authentication.getPrincipal();
        }
        return null;
    }
}
