package com.markethub.app.config;

import com.markethub.app.repository.ShoppingCartRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final ShoppingCartRepository shoppingCartRepository;

    public WebMvcConfig(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CartBadgeInterceptor(shoppingCartRepository))
                .addPathPatterns("/onlinemarket/secured/**", "/onlinemarket/cart/**", "/orders/**");
    }
}
