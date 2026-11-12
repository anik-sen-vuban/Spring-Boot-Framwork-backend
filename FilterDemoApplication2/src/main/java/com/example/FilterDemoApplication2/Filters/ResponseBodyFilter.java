package com.example.FilterDemoApplication2.Filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

//@Component
//@Order(2)
public class ResponseBodyFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {
        HttpServletResponse httpResponse =
                (HttpServletResponse) response;
        ContentCachingResponseWrapper wrappedResponse =
                new ContentCachingResponseWrapper(httpResponse);

        //Downstream code writes into the wrapper
        chain.doFilter(request, wrappedResponse);

        byte[] originalBodyBytes =
                wrappedResponse.getContentAsByteArray();
        String originalBody =
                new String(
                        originalBodyBytes,
                        StandardCharsets.UTF_8
                );
        String modifiedBody =
                """
                {
                    "originalResponse": %s,
                    "processedBy": "ResponseBodyFilter"
                }
                """.formatted(originalBody);

        //Clears the currently cached Body, but keeps status and headers
        wrappedResponse.resetBuffer();
        wrappedResponse.setContentType("application/json");
        wrappedResponse.getWriter().write(modifiedBody);

        //Copies the cached final body into the actual response
        wrappedResponse.copyBodyToResponse();
    }
}
