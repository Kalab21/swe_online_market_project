package edu.miu.cs.cs425.swe_online_market_project.service;

import edu.miu.cs.cs425.swe_online_market_project.model.Product;
import edu.miu.cs.cs425.swe_online_market_project.repository.ProductRepository;
import edu.miu.cs.cs425.swe_online_market_project.service.imp.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock private ProductRepository productRepository;
    @InjectMocks private ProductServiceImpl productService;

    @Test
    void getAllProducts_returnsList() {
        when(productRepository.findAll()).thenReturn(List.of(new Product(), new Product()));

        assertEquals(2, productService.getAllProducts().size());
    }

    @Test
    void getProductById_success() {
        Product product = new Product();
        product.setProductId(1L);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Product result = productService.getProductById(1L);

        assertEquals(1L, result.getProductId());
    }

    @Test
    void getProductById_notFound_throwsException() {
        when(productRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> productService.getProductById(99L));
    }

    @Test
    void saveProduct_returnsSavedProduct() {
        Product product = new Product();
        product.setName("Test Product");
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product result = productService.saveProduct(product);

        assertEquals("Test Product", result.getName());
        verify(productRepository).save(product);
    }

    @Test
    void deleteById_callsRepository() {
        doNothing().when(productRepository).deleteById(1L);

        productService.deleteById(1L);

        verify(productRepository).deleteById(1L);
    }

    @Test
    void searchProducts_returnsMatchingList() {
        Product p = new Product();
        p.setName("Laptop");
        when(productRepository.findAllByProductName("Laptop")).thenReturn(List.of(p));

        List<Product> results = productService.searchProducts("Laptop");

        assertEquals(1, results.size());
        assertEquals("Laptop", results.get(0).getName());
    }
}
