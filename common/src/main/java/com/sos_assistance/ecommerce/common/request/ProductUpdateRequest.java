package com.sos_assistance.ecommerce.common.request;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProductUpdateRequest {
    private BigDecimal price;
}
