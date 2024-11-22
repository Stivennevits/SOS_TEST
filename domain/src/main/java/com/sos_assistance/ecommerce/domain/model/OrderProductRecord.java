package com.sos_assistance.ecommerce.domain.model;

import com.sos_assistance.ecommerce.domain.pk.OrderProductPK;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "order_product_quantity")
public class OrderProductRecord {
    @EmbeddedId
    private OrderProductPK id;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private OrderRecord orderRecord;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private ProductRecord productRecord;

    @Column(nullable = false)
    private Integer quantity;
}
