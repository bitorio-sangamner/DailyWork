package dev.rsm.config;

import dev.rsm.exceptions.UnAuthorizedAccessException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;

import java.io.IOException;

public class AuthHeaderFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @SneakyThrows
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String customHeader = httpServletRequest.getHeader("REQUEST-FOR");

        if (customHeader != null && (customHeader.equals("user") || customHeader.equals("forgot"))) {
            chain.doFilter(request, response);
        } else {
            throw new UnAuthorizedAccessException("You are not authorized for this service", 401);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

