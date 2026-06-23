package com.markethub.app.config;

import com.markethub.app.repository.ShoppingCartRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class CartBadgeInterceptor implements HandlerInterceptor {

    private final ShoppingCartRepository shoppingCartRepository;

    public CartBadgeInterceptor(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) {
        if (modelAndView == null || !modelAndView.hasView()) return;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || auth instanceof AnonymousAuthenticationToken) return;

        boolean isBuyer = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_BUYER"));
        if (!isBuyer) return;

        try {
            shoppingCartRepository.findByBuyerUserName(auth.getName()).ifPresent(cart -> {
                int count = cart.getProducts() != null ? cart.getProducts().size() : 0;
                modelAndView.addObject("cartItemCount", count);
            });
        } catch (Exception ignored) {}
    }
}
