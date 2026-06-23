package com.markethub.app.service.imp;

import com.markethub.app.model.Product;
import com.markethub.app.model.ShoppingCart;
import com.markethub.app.repository.ShoppingCartRepository;
import com.markethub.app.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }


    @Override
    public ShoppingCart getShoppingCartById(long id) {
        return shoppingCartRepository.getById(id);

    }

    @Override
    public void deleteShoppingCartById(long id) {
        shoppingCartRepository.deleteById(id);

    }

    @Override
    public ShoppingCart getShoppingCartByBuyer(Long id) {
        return shoppingCartRepository.findByBuyerUserId(id)
                .orElseThrow(() -> new RuntimeException("Cart not found for buyer: " + id));
    }

    @Override
    public ShoppingCart addProductToShoppingCart(Long cartId, Product product) {
        ShoppingCart cart = shoppingCartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found: " + cartId));
        List<Product> products = cart.getProducts();
        products.add(product);
        cart.setProducts(products);
        return shoppingCartRepository.save(cart);
    }

    @Override
    public void deleteProductFromCart(Long productId, Long cartId) {
        ShoppingCart cart = shoppingCartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found: " + cartId));
        List<Product> products = cart.getProducts();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductId() == productId) {
                products.remove(i);
                break;
            }
        }
        cart.setProducts(products);
        shoppingCartRepository.save(cart);
    }

    @Override
    public void deleteAllProductsFromCart(Long cartId) {
        ShoppingCart cart = shoppingCartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found: " + cartId));
        List<Product> products = cart.getProducts();
        products.clear();
        cart.setProducts(products);
        shoppingCartRepository.save(cart);
    }

}
