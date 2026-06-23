package com.markethub.app.service;

import com.markethub.app.model.Product;
import com.markethub.app.model.ShoppingCart;

public interface ShoppingCartService {
    ShoppingCart getShoppingCartById(long id);

    void deleteShoppingCartById(long id);

    ShoppingCart getShoppingCartByBuyer(Long id);

    ShoppingCart addProductToShoppingCart(Long cartId, Product product);

    void deleteProductFromCart(Long productId, Long cartId);

    void deleteAllProductsFromCart(Long cartId);
}
