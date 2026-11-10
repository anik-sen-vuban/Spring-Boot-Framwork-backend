package com.example.FilterDemoApplication.Filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(1)
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain
    ) throws IOException, ServletException {
//        System.out.println(
//                "Authentication before"
//        );
        HttpServletRequest httpRequest =
                (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse =
                (HttpServletResponse) servletResponse;

        String token = httpRequest.getHeader(("token"));
        String apikey = httpRequest.getHeader("x-api-key");
        String method = httpRequest.getMethod();
        String uri = httpRequest.getRequestURI();
        String remoteAddress = httpRequest.getRemoteAddr();
        String userAgent = httpRequest.getHeader("User-Agent");

        // Request Information
        System.out.println("========== Incoming Request ==========");
        System.out.println("Method: " + method);
        System.out.println("URI: " + uri);
        System.out.println("Remote: " + remoteAddress);
        System.out.println("User-Agent: " + userAgent);

        if(token == null || !token.equals("12345")){
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        if(apikey == null || !apikey.equals("secret123")){
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            httpResponse.setContentType("application/json");
            httpResponse.getWriter().write(
                    "{\n" +
                            "    \"message\": \"Invalid or missing Api key\"\n"
                        + "}"
            );
            return;
        }

        // Custom Response Headers
        httpResponse.setHeader("X-Application-Name", "Student Service");
        httpResponse.setHeader("X-Content-Type-Options", "nosniff");

        Long startTime =
                System.currentTimeMillis();

        filterChain.doFilter(servletRequest, servletResponse);

        Long duration =
                System.currentTimeMillis() - startTime;

        System.out.println(
                "Duration between Filter1 to Filter2: " + duration +" ms"
        );

//        System.out.println(
//                "Authentication after"
//        );
        System.out.println(
                "Response status: "
                + httpResponse.getStatus()
        );

    }
}
