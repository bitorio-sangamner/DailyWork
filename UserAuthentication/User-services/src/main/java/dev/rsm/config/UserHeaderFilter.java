package dev.rsm.config;

import dev.rsm.exception.UnAuthorizedAccessException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;

import java.io.IOException;

public class UserHeaderFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @SneakyThrows
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletRespose = (HttpServletResponse) response;
        String customHeader = httpServletRequest.getHeader("REQUEST-FOR");

        if (customHeader != null && (customHeader.equals("user") || customHeader.equals("forgot"))) {
            chain.doFilter(request, response);
        } else {
            httpServletRespose.sendError(HttpStatus.UNAUTHORIZED.value(), "You are not authorized for this service");
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
