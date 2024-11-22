package com.sos_assistance.ecommerce.core.service;

import com.sos_assistance.ecommerce.common.constants.ErrorMessages;
import com.sos_assistance.ecommerce.common.ex.SosAssistanceException;
import com.sos_assistance.ecommerce.common.mappers.OrderMapper;
import com.sos_assistance.ecommerce.common.request.OrderRequest;
import com.sos_assistance.ecommerce.core.components.I18NComponent;
import com.sos_assistance.ecommerce.domain.IOrderDto;
import com.sos_assistance.ecommerce.domain.model.OrderProductRecord;
import com.sos_assistance.ecommerce.domain.model.OrderRecord;
import com.sos_assistance.ecommerce.domain.model.ProductRecord;
import com.sos_assistance.ecommerce.domain.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.IntStream;

import static com.sos_assistance.ecommerce.domain.enums.OrderStatus.*;

@Slf4j
@Service
public class OrderService {
    private final I18NComponent i18NComponent;
    private final OrderRepository repository;
    private final ProductService productService;
    private final OrderProductService orderProductService;


    public OrderService(I18NComponent i18NComponent, OrderRepository repository, ProductService productService, OrderProductService orderProductService) {
        this.i18NComponent = i18NComponent;
        this.repository = repository;
        this.productService = productService;
        this.orderProductService = orderProductService;
    }
    public List<IOrderDto> getAll() {
        log.info("OrderService::getAll ");
        return repository.getAll();
    }
    public Page<IOrderDto> getByFilter(String status, LocalDate fromAt, LocalDate toAt, PageRequest pageable) {
        log.info("OrderService::getByFilter --status: [{}] --fromAt: [{}] --toAt: [{}]", status, fromAt, toAt);
        return repository.getByFilter(status, fromAt, toAt, pageable);
    }
    public OrderRecord getById(Long id) {
        log.info("OrderService::getById --id: [{}] ", id);
        return repository.findById(id).orElseThrow(() -> new SosAssistanceException(i18NComponent.getMessage(ErrorMessages.ORDER_NOT_FOUND, id)));
    }
    @Transactional
    public void create(OrderRequest request) {
        log.info("OrderService::create --request: [{}]", request);
        if (request.getProductIds().size() != request.getQuantities().size()) {
            throw new SosAssistanceException(i18NComponent.getMessage(
                    ErrorMessages.INVALID_ORDER,
                    request.getProductIds().size(),
                    request.getQuantities().size()));
        }
        OrderRecord orderRecord = repository.save(OrderMapper.mapToCreate());

        try {
            IntStream.range(0, request.getProductIds().size())
                    .forEach(i -> {
                        Long productId = request.getProductIds().get(i);
                        Integer quantity = request.getQuantities().get(i);
                        ProductRecord productRecord = productService.getById(productId);
                        OrderProductRecord orderProductRecord = orderProductService.create(orderRecord, productRecord, quantity);
                        orderRecord.getOrderProductRecords().add(orderProductRecord);
                    });
        } catch (Exception e) {
            throw new SosAssistanceException(i18NComponent.getMessage(
                    "Error processing the order while associating products. Cause: " + e.getMessage(),
                    e));
        }
    }

    public void updateStatus(Long id, String status) {
        log.info("OrderService::updateStatus --id: [{}] --status: [{}]", id,status);
        if(!(status.equals(PENDING.name()) || status.equals(IN_PROCESS.name()) || status.equals(COMPLETED.name()))){
            throw new SosAssistanceException(i18NComponent.getMessage(ErrorMessages.INVALID_STATUS));
        }
        OrderRecord orderRecord = getById(id);
        repository.save(OrderMapper.mapToUpdateStatus(orderRecord, status));
    }
    public void softDelete(Long id) {
        log.info("OrderController::softDelete --id: [{}]", id);
        OrderRecord orderRecord = getById(id);
        repository.save(OrderMapper.mapToSoftDelete(orderRecord));
    }



}
