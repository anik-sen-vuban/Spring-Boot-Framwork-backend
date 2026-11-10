package com.example.FilterDemoApplication.Filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
@Order(2)
public class LoggingFilter implements Filter {
    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {

        Long startTime = System.currentTimeMillis();
        HttpServletRequest httpServletRequest =
                (HttpServletRequest) request;
        HttpServletResponse httpServletResponse =
                (HttpServletResponse) response;

        String requestedId = UUID.randomUUID().toString();

        httpServletResponse.setHeader("X-Request-ID", requestedId);

        //Request log
        System.out.println(
                "Incoming Request : " +
                        httpServletRequest.getMethod() + " " +
                        httpServletRequest.getRequestURI()
        );

        try{
            chain.doFilter(request, response);
        }finally{
            Long duration = System.currentTimeMillis() - startTime;

            //Response status log
            System.out.println(
                    "Response status: " +
                            httpServletResponse.getStatus()
            );
            System.out.println(
                    "API Response time: " + duration
            );
        }

    }
}
