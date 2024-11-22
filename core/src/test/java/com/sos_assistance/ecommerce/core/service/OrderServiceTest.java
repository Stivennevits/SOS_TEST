package com.sos_assistance.ecommerce.core.service;

import com.sos_assistance.ecommerce.common.constants.ErrorMessages;
import com.sos_assistance.ecommerce.common.ex.SosAssistanceException;
import com.sos_assistance.ecommerce.common.request.OrderRequest;
import com.sos_assistance.ecommerce.core.components.I18NComponent;
import com.sos_assistance.ecommerce.domain.IOrderDto;
import com.sos_assistance.ecommerce.domain.model.OrderProductRecord;
import com.sos_assistance.ecommerce.domain.model.OrderRecord;
import com.sos_assistance.ecommerce.domain.model.ProductRecord;
import com.sos_assistance.ecommerce.domain.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private I18NComponent i18NComponent;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductService productService;

    @Mock
    private OrderProductService orderProductService;

    @InjectMocks
    private OrderService orderService;

    private OrderRecord orderRecord;
    private OrderRequest orderRequest;

    @BeforeEach
    void setUp() {
        orderRecord = new OrderRecord();
        orderRecord.setId(1L);

        orderRequest = new OrderRequest();
        orderRequest.setProductIds(Arrays.asList(1L, 2L));
        orderRequest.setQuantities(Arrays.asList(2, 3));
    }



    @Test
    void testGetByFilter() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        when(orderRepository.getByFilter("PENDING", LocalDate.now(), LocalDate.now(), pageRequest))
                .thenReturn(Page.empty());
        Page<IOrderDto> result = orderService.getByFilter("PENDING", LocalDate.now(), LocalDate.now(), pageRequest);
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(orderRepository).getByFilter("PENDING", LocalDate.now(), LocalDate.now(), pageRequest);
    }

    @Test
    void testGetById() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(orderRecord));
        OrderRecord result = orderService.getById(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(orderRepository).findById(1L);
    }

    @Test
    void testGetById_OrderNotFound() {
        when(orderRepository.findById(1L)).thenReturn(Optional.empty());
        when(i18NComponent.getMessage(ErrorMessages.ORDER_NOT_FOUND, 1L)).thenReturn("Order not found");
        SosAssistanceException exception = assertThrows(SosAssistanceException.class, () -> {
            orderService.getById(1L);
        });

        assertEquals("Order not found", exception.getMessage());
        verify(orderRepository).findById(1L);
    }

    @Test
    void testCreate() {
        when(orderRepository.save(any(OrderRecord.class))).thenReturn(orderRecord);
        when(productService.getById(1L)).thenReturn(new ProductRecord());
        when(productService.getById(2L)).thenReturn(new ProductRecord());
        when(orderProductService.create(any(OrderRecord.class), any(ProductRecord.class), anyInt()))
                .thenReturn(new OrderProductRecord());

        orderService.create(orderRequest);

        verify(orderRepository).save(any(OrderRecord.class));
        verify(productService).getById(1L);
        verify(productService).getById(2L);
        verify(orderProductService, times(2)).create(any(OrderRecord.class), any(ProductRecord.class), anyInt()); // espera que se llame dos veces
    }
    @Test
    void testCreate_InvalidOrder() {
        orderRequest.setProductIds(Arrays.asList(1L));
        SosAssistanceException exception = assertThrows(SosAssistanceException.class, () -> {
            orderService.create(orderRequest);
        });
        assertNotNull(exception);
        verify(orderRepository, never()).save(any(OrderRecord.class));
    }

    @Test
    void testUpdateStatus() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(orderRecord));
        when(orderRepository.save(any(OrderRecord.class))).thenReturn(orderRecord);
        orderService.updateStatus(1L, "PENDING");
        verify(orderRepository).findById(1L);
        verify(orderRepository).save(any(OrderRecord.class));
    }

    @Test
    void testUpdateStatus_InvalidStatus() {
        SosAssistanceException exception = assertThrows(SosAssistanceException.class, () -> {
            orderService.updateStatus(1L, "INVALID_STATUS");
        });

        assertNotNull(exception);
        verify(orderRepository, never()).save(any(OrderRecord.class));
    }

    @Test
    void testSoftDelete() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(orderRecord));
        when(orderRepository.save(any(OrderRecord.class))).thenReturn(orderRecord);
        orderService.softDelete(1L);
        verify(orderRepository).findById(1L);
        verify(orderRepository).save(any(OrderRecord.class));
    }
}
