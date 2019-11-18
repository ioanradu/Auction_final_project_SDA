package com.sda.auction.config;

import com.sda.auction.service.SecurityService;
import com.sda.auction.service.impl.AccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component
@Order(1)
public class SecurityFilter implements Filter {
    @Autowired
    private SecurityService securityService;
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        if (securityService.isValid(servletRequest)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            accessDeniedHandler.reply(servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
