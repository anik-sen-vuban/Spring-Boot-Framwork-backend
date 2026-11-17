package com.example.InterceptorDemoApplication.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.awt.*;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    )throws Exception{
//        System.out.println("AuthentiationInterceptor preHandle executed");

        String apiKey = request.getHeader("x-api-key");
        if(apiKey == null){
            response.setStatus(
                            HttpServletResponse.SC_UNAUTHORIZED
                    );
            response.setContentType(
                    MediaType.APPLICATION_JSON_VALUE
            );
            response.getWriter().write(
                    "{\n" +
                            "    \"message\":\"API Key is missing\"\n" +
                            "}"
            );
            return false;
        }
        if(!apiKey.equals("secret123")){
            response.setStatus(
                    HttpServletResponse.SC_UNAUTHORIZED
            );
            response.setContentType(
                    MediaType.APPLICATION_JSON_VALUE
            );
            response.getWriter().write(
                    "{\n" +
                            "    \"message\":\"Invalid API key\"\n" +
                            "}"
            );
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            ModelAndView modelAndView) throws Exception {
        System.out.println("AuthenticationInterceptor postHandle executed");
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception ex) throws Exception {
        System.out.println("AuthenticationInterceptor afterCompletion executed");
    }
}
