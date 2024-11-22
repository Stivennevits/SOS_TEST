package com.sos_assistance.ecommerce.domain;

import java.time.LocalDateTime;

public interface IOrderDto {
   Long getId();
   String getName();
   int getQuantity();
   String getStatus();
    LocalDateTime getCreated();
    LocalDateTime getUpdated();
}
