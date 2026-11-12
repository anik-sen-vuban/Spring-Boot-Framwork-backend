package com.example.FilterDemoApplication2.Filters;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

//@Component
//@Order(1)
public class ResponseHeaderFilter implements Filter{

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {
        HttpServletRequest httpServletRequest =
                (HttpServletRequest) request;
        HttpServletResponse httpServletResponse =
                (HttpServletResponse) response;

        String requestId = UUID.randomUUID().toString();

        httpServletResponse.setHeader(
                "X-Application-Name", "Student Management API"
        );
        httpServletResponse.setHeader(
                "X-Request-Id", requestId
        );
        httpServletResponse.addHeader(
                "X-Feature", "Students"
        );
        httpServletResponse.addHeader(
                "X-Feature", "Coureses"
        );

        boolean committed = httpServletResponse.isCommitted();
        System.out.println("Is Committed: " + committed);

        chain.doFilter(request, response);
        committed = httpServletResponse.isCommitted();
        System.out.println("Is Committed: " + committed);
    }
}
