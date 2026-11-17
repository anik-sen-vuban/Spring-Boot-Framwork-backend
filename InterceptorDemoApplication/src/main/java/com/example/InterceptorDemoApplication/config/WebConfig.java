package com.example.InterceptorDemoApplication.config;

import com.example.InterceptorDemoApplication.interceptor.AuthenticationInterceptor;
import com.example.InterceptorDemoApplication.interceptor.AuthorizationInterceptor;
import com.example.InterceptorDemoApplication.interceptor.LoggingInteceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final LoggingInteceptor loggingInteceptor;
    private final AuthenticationInterceptor authenticationInterceptor;
    private final AuthorizationInterceptor authorizationInterceptor;

    public WebConfig(
            LoggingInteceptor loggingInteceptor,
            AuthenticationInterceptor authenticationInterceptor,
            AuthorizationInterceptor authorizationInterceptor

    ){
        this.loggingInteceptor = loggingInteceptor;
        this.authenticationInterceptor = authenticationInterceptor;
        this.authorizationInterceptor = authorizationInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingInteceptor)
                .order(2);

        registry.addInterceptor(authenticationInterceptor)
                .order(1);

        registry.addInterceptor(authorizationInterceptor)
                .order(3);
    }
}
