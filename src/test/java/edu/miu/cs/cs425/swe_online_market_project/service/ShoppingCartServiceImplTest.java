package edu.miu.cs.cs425.swe_online_market_project.service;

import edu.miu.cs.cs425.swe_online_market_project.model.Product;
import edu.miu.cs.cs425.swe_online_market_project.model.ShoppingCart;
import edu.miu.cs.cs425.swe_online_market_project.repository.ShoppingCartRepository;
import edu.miu.cs.cs425.swe_online_market_project.service.imp.ShoppingCartServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShoppingCartServiceImplTest {

    @Mock private ShoppingCartRepository shoppingCartRepository;
    @InjectMocks private ShoppingCartServiceImpl shoppingCartService;

    private ShoppingCart cartWithProducts(Long cartId, List<Product> products) {
        ShoppingCart cart = new ShoppingCart();
        cart.setCartId(cartId);
        cart.setProducts(new ArrayList<>(products));
        return cart;
    }

    @Test
    void addProductToCart_success() {
        Product product = new Product();
        product.setProductId(10L);
        ShoppingCart cart = cartWithProducts(1L, new ArrayList<>());

        when(shoppingCartRepository.findById(1L)).thenReturn(Optional.of(cart));
        when(shoppingCartRepository.save(cart)).thenReturn(cart);

        ShoppingCart result = shoppingCartService.addProductToShoppingCart(1L, product);

        assertEquals(1, result.getProducts().size());
        verify(shoppingCartRepository).save(cart);
    }

    @Test
    void addProductToCart_cartNotFound_throwsException() {
        when(shoppingCartRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> shoppingCartService.addProductToShoppingCart(99L, new Product()));
        verify(shoppingCartRepository, never()).save(any());
    }

    @Test
    void deleteProductFromCart_success() {
        Product product = new Product();
        product.setProductId(5L);
        ShoppingCart cart = cartWithProducts(1L, List.of(product));

        when(shoppingCartRepository.findById(1L)).thenReturn(Optional.of(cart));

        shoppingCartService.deleteProductFromCart(5L, 1L);

        assertTrue(cart.getProducts().isEmpty());
        verify(shoppingCartRepository).save(cart);
    }

    @Test
    void deleteAllProductsFromCart_success() {
        Product p1 = new Product(); p1.setProductId(1L);
        Product p2 = new Product(); p2.setProductId(2L);
        ShoppingCart cart = cartWithProducts(1L, List.of(p1, p2));

        when(shoppingCartRepository.findById(1L)).thenReturn(Optional.of(cart));

        shoppingCartService.deleteAllProductsFromCart(1L);

        assertTrue(cart.getProducts().isEmpty());
        verify(shoppingCartRepository).save(cart);
    }

    @Test
    void getShoppingCartByBuyer_notFound_throwsException() {
        when(shoppingCartRepository.findByBuyerUserId(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> shoppingCartService.getShoppingCartByBuyer(99L));
    }
}
