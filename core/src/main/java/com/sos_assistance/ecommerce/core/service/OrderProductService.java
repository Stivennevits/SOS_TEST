package com.sos_assistance.ecommerce.core.service;

import com.sos_assistance.ecommerce.common.mappers.OrderProductMapper;
import com.sos_assistance.ecommerce.domain.model.OrderProductRecord;
import com.sos_assistance.ecommerce.domain.model.OrderRecord;
import com.sos_assistance.ecommerce.domain.model.ProductRecord;
import com.sos_assistance.ecommerce.domain.repository.OrderProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderProductService {

    private final OrderProductRepository repository;

    public OrderProductService(OrderProductRepository repository) {
        this.repository = repository;
    }

    public OrderProductRecord create(OrderRecord orderRecord, ProductRecord productRecord, int quantity){
        log.info("OrderProductService::create --orderRecord: [{}] --productRecord: [{}] --quantity: [{}]", orderRecord,productRecord,quantity);
        OrderProductRecord orderProductRecord = repository.save(OrderProductMapper.mapToCreate(orderRecord,productRecord,quantity));
        return orderProductRecord;
    }
}
