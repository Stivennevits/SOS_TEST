package com.sos_assistance.ecommerce.common.mappers;

import com.sos_assistance.ecommerce.common.request.ProductRequest;
import com.sos_assistance.ecommerce.common.request.ProductUpdateRequest;
import com.sos_assistance.ecommerce.domain.model.ProductRecord;

public class ProductMapper {
    private ProductMapper() {
        throw new IllegalStateException("ProductMapper");
    }

    public static ProductRecord mapToCreate(ProductRequest request) {
        ProductRecord data = new ProductRecord();
        data.setName(request.getName());
        data.setPrice(request.getPrice());
        return data;
    }

    public static ProductRecord mapToUpdate(ProductRecord productRecord, ProductUpdateRequest request) {
        productRecord.setPrice(request.getPrice());
        return productRecord;
    }
}
