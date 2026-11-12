package com.example.FilterDemoApplication2.Filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class RequestFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {
        HttpServletRequest httpRequest =
                (HttpServletRequest) request;

        ContentCachingRequestWrapper wrappedRequest =
                new ContentCachingRequestWrapper(httpRequest, 10240);

        chain.doFilter(wrappedRequest, response);

        byte[] originalBodyBytes =
                wrappedRequest.getContentAsByteArray();
        StringBuilder body = new StringBuilder();

        String requestBody =
                new String(originalBodyBytes, StandardCharsets.UTF_8);

        body.append(requestBody);

        System.out.println(body);
    }
}
