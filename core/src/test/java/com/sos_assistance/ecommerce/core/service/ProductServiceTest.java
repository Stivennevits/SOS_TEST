package com.sos_assistance.ecommerce.core.service;

import com.sos_assistance.ecommerce.common.ex.SosAssistanceException;
import com.sos_assistance.ecommerce.common.mappers.ProductMapper;
import com.sos_assistance.ecommerce.common.request.ProductRequest;
import com.sos_assistance.ecommerce.domain.model.ProductRecord;
import com.sos_assistance.ecommerce.domain.repository.ProductRepository;
import com.sos_assistance.ecommerce.core.components.I18NComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.math.BigDecimal;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private I18NComponent i18NComponent;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetById_ProductFound() {
        Long productId = 1L;
        ProductRecord product = new ProductRecord();
        product.setId(productId);
        product.setName("Test Product");
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        ProductRecord result = productService.getById(productId);

        assertNotNull(result);
        assertEquals(productId, result.getId());
        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    void testGetById_ProductNotFound() {
        Long productId = 1L;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());
        when(i18NComponent.getMessage(anyString(), anyLong())).thenReturn("Product not found");

        SosAssistanceException exception = assertThrows(SosAssistanceException.class, () -> productService.getById(productId));
        assertEquals("Product not found", exception.getMessage());
        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    void testCreate_ProductAlreadyExists() {
        ProductRequest request = new ProductRequest();
        request.setName("Existing Product");
        when(productRepository.findByName(request.getName())).thenReturn(Optional.of(new ProductRecord()));
        when(i18NComponent.getMessage(anyString(), anyString())).thenReturn("Product already exists");

        SosAssistanceException exception = assertThrows(SosAssistanceException.class, () -> productService.create(request));
        assertEquals("Product already exists", exception.getMessage());
        verify(productRepository, times(1)).findByName(request.getName());
    }

    @Test
    void testCreate_NewProduct() {
        ProductRequest request = new ProductRequest();
        request.setName("New Product");
        request.setPrice(new BigDecimal("99.99"));
        when(productRepository.findByName(request.getName())).thenReturn(Optional.empty());
        when(productRepository.save(any(ProductRecord.class))).thenReturn(ProductMapper.mapToCreate(request));

        ProductRecord result = productService.create(request);

        assertNotNull(result);
        assertEquals(request.getName(), result.getName());
        verify(productRepository, times(1)).findByName(request.getName());
        verify(productRepository, times(1)).save(any(ProductRecord.class));
    }

}
