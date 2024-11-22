package com.sos_assistance.ecommerce.domain.pk;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@Embeddable
@EqualsAndHashCode
public class OrderProductPK implements Serializable {
    private Long orderId;
    private Long productId;
}
