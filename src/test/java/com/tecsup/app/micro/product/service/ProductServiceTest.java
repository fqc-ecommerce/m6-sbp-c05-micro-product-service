package com.tecsup.app.micro.product.service;

import com.tecsup.app.micro.product.client.User;
import com.tecsup.app.micro.product.client.UserClient;
import com.tecsup.app.micro.product.dto.Product;
import com.tecsup.app.micro.product.entity.ProductEntity;
import com.tecsup.app.micro.product.mapper.ProductMapper;
import com.tecsup.app.micro.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @Mock
    private UserClient userClient;

    @InjectMocks
    private ProductService productService;

    private ProductEntity productEntity;
    private Product product;
    private User user;

    @BeforeEach
    void setUp() {
        // Initialize test data
        user = User.builder()
                .id(1L)
                .name("Test User")
                .email("test@example.com")
                .build();

        productEntity = new ProductEntity();
        productEntity.setId(1L);
        productEntity.setName("Test Product");
        productEntity.setDescription("Test Description");
        productEntity.setPrice(new BigDecimal("99.99"));
        productEntity.setStock(10);
        productEntity.setCategory("Test Category");
        productEntity.setCreatedBy(1L);

        product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setDescription("Test Description");
        product.setPrice(new BigDecimal("99.99"));
        product.setStock(10);
        product.setCategory("Test Category");
        product.setCreatedByUser(user);
    }

    @Test
    void getProductById_WhenProductExists_ShouldReturnProduct() {
        // Arrange
        when(productRepository.findById(1L)).thenReturn(Optional.of(productEntity));
        when(userClient.getUserById(1L)).thenReturn(user);
        when(productMapper.toDomainWithUser(productEntity, user)).thenReturn(product);

        // Act
        Product result = productService.getUserById(1L);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getName()).isEqualTo("Test Product");
        assertThat(result.getDescription()).isEqualTo("Test Description");
        assertThat(result.getPrice()).isEqualByComparingTo("99.99");
        assertThat(result.getStock()).isEqualTo(10);
        assertThat(result.getCategory()).isEqualTo("Test Category");
        assertThat(result.getCreatedByUser())
            .isNotNull()
            .extracting(User::getId, User::getName, User::getEmail)
            .containsExactly(1L, "Test User", "test@example.com");
        
        verify(productRepository).findById(1L);
        verify(userClient).getUserById(1L);
        verifyNoMoreInteractions(productRepository, userClient, productMapper);
    }

    @Test
    void getProductById_WhenProductDoesNotExist_ShouldReturnNull() {
        // Arrange
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        Product result = productService.getUserById(1L);

        // Assert
        assertThat(result).isNull();
        verify(productRepository).findById(1L);
        verifyNoInteractions(userClient, productMapper);
        verifyNoMoreInteractions(productRepository);
    }

    @Test
    void createProduct_WithValidData_ShouldReturnCreatedProduct() {
        // This test would be implemented when the create method is added to ProductService
        // For now, it's a placeholder
        assertTrue(true, "Test for createProduct will be implemented when the method is available");
    }

    @Test
    void updateProduct_WithValidData_ShouldReturnUpdatedProduct() {
        // This test would be implemented when the update method is added to ProductService
        // For now, it's a placeholder
        assertTrue(true, "Test for updateProduct will be implemented when the method is available");
    }

    @Test
    void deleteProduct_WithValidId_ShouldDeleteProduct() {
        // This test would be implemented when the delete method is added to ProductService
        // For now, it's a placeholder
        assertTrue(true, "Test for deleteProduct will be implemented when the method is available");
    }

    @Test
    void getAllProducts_ShouldReturnListOfProducts() {
        // This test would be implemented when the getAll method is added to ProductService
        // For now, it's a placeholder
        assertTrue(true, "Test for getAllProducts will be implemented when the method is available");
    }
}