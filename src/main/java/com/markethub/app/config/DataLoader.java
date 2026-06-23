package com.markethub.app.config;

import com.markethub.app.model.Product;
import com.markethub.app.model.Role;
import com.markethub.app.model.ShoppingCart;
import com.markethub.app.model.User;
import com.markethub.app.repository.ProductRepository;
import com.markethub.app.repository.RoleRepository;
import com.markethub.app.repository.ShoppingCartRepository;
import com.markethub.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired private RoleRepository roleRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private ProductRepository productRepository;
    @Autowired private ShoppingCartRepository shoppingCartRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) {
        Role adminRole  = getOrCreateRole("ROLE_ADMIN");
        Role sellerRole = getOrCreateRole("ROLE_SELLER");
        Role buyerRole  = getOrCreateRole("ROLE_BUYER");

        getOrCreateUser("admin",  "Admin", "User",   "admin@market.com",  "password", false, List.of(adminRole),  false);
        User seller = getOrCreateUser("seller", "John", "Seller", "seller@market.com", "password", true, List.of(sellerRole), false);
        getOrCreateUser("buyer",  "Jane",  "Buyer",  "buyer@market.com",  "password", false, List.of(buyerRole),  true);

        if (productRepository.count() == 0) {
            seedProducts(seller);
        }
    }

    private Role getOrCreateRole(String roleType) {
        return roleRepository.findByRoleType(roleType).orElseGet(() -> {
            Role r = new Role();
            r.setRoleType(roleType);
            return roleRepository.save(r);
        });
    }

    private User getOrCreateUser(String username, String first, String last,
                                  String email, String rawPassword,
                                  boolean approvedSeller, List<Role> roles,
                                  boolean createCart) {
        return userRepository.findByUserName(username).orElseGet(() -> {
            User u = new User();
            u.setFirstName(first);
            u.setLastName(last);
            u.setUserName(username);
            u.setEmail(email);
            u.setPassword(passwordEncoder.encode(rawPassword));
            u.setApprovedSeller(approvedSeller);
            u.setRoles(roles);
            if (createCart) {
                ShoppingCart cart = new ShoppingCart();
                u.setShoppingCart(shoppingCartRepository.save(cart));
            }
            return userRepository.save(u);
        });
    }

    private void seedProducts(User seller) {
        saveProduct("Wireless Headphones",  49.99, "Noise-cancelling over-ear headphones", 25, "SKU-001", seller);
        saveProduct("Mechanical Keyboard",  89.95, "RGB backlit tenkeyless keyboard",       15, "SKU-002", seller);
        saveProduct("USB-C Hub 7-in-1",     34.99, "4K HDMI, USB 3.0, SD card reader",     40, "SKU-003", seller);
        saveProduct("Laptop Stand Aluminum",29.99, "Adjustable ergonomic aluminum stand",   30, "SKU-004", seller);
        saveProduct("Webcam 1080p",         59.00, "Full HD webcam with built-in mic",      20, "SKU-005", seller);
        saveProduct("Mouse Pad XL",         14.99, "Extended gaming desk mat 900x400mm",    50, "SKU-006", seller);
    }

    private void saveProduct(String name, double price, String desc, int qty, String sku, User seller) {
        Product p = new Product();
        p.setName(name);
        p.setPrice(price);
        p.setDescription(desc);
        p.setQuantity(qty);
        p.setSku(sku);
        p.setSeller(seller);
        productRepository.save(p);
    }
}
