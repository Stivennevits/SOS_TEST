package com.sos_assistance.ecommerce.common.mappers;

import com.sos_assistance.ecommerce.domain.model.OrderProductRecord;
import com.sos_assistance.ecommerce.domain.model.OrderRecord;
import com.sos_assistance.ecommerce.domain.model.ProductRecord;
import com.sos_assistance.ecommerce.domain.pk.OrderProductPK;

public class OrderProductMapper {
    private OrderProductMapper() {
        throw new IllegalStateException("OrderProductMapper");
    }

    public static OrderProductRecord mapToCreate(OrderRecord orderRecord, ProductRecord productRecord, int quantity){
        OrderProductRecord orderProductRecord = new OrderProductRecord();
        OrderProductPK id = new OrderProductPK();
        id.setOrderId(orderRecord.getId());
        id.setProductId(productRecord.getId());
        orderProductRecord.setId(id);
        orderProductRecord.setOrderRecord(orderRecord);
        orderProductRecord.setProductRecord(productRecord);
        orderProductRecord.setQuantity(quantity);
        return orderProductRecord;
    }
}
