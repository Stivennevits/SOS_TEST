package com.sos_assistance.ecommerce.common.mappers;

import com.sos_assistance.ecommerce.common.request.OrderRequest;
import com.sos_assistance.ecommerce.common.request.ProductRequest;
import com.sos_assistance.ecommerce.domain.enums.OrderStatus;
import com.sos_assistance.ecommerce.domain.model.OrderRecord;
import com.sos_assistance.ecommerce.domain.model.ProductRecord;

import java.time.LocalDateTime;

public class OrderMapper {
    private OrderMapper() {
        throw new IllegalStateException("OrderMapper");
    }

    public static OrderRecord mapToCreate() {
        OrderRecord data = new OrderRecord();
        data.setCreatedAt(LocalDateTime.now());
        data.setStatus(OrderStatus.PENDING);
        return data;
    }

    public static OrderRecord mapToUpdateStatus(OrderRecord orderRecord, String status) {
        orderRecord.setStatus(OrderStatus.valueOf(status));
        orderRecord.setUpdatedAt(LocalDateTime.now());
        return orderRecord;
    }

    public static OrderRecord mapToSoftDelete(OrderRecord orderRecord) {
        orderRecord.setStatus(OrderStatus.CANCELLED);
        orderRecord.setUpdatedAt(LocalDateTime.now());
        return orderRecord;
    }
}
