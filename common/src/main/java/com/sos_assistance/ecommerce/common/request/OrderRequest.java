package com.sos_assistance.ecommerce.common.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OrderRequest {
    List<Long> productIds;
    List<Integer> quantities;
}
