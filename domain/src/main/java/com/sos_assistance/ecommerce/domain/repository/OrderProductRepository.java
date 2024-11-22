package com.sos_assistance.ecommerce.domain.repository;

import com.sos_assistance.ecommerce.domain.model.OrderProductRecord;
import com.sos_assistance.ecommerce.domain.pk.OrderProductPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProductRecord, OrderProductPK> {
}
