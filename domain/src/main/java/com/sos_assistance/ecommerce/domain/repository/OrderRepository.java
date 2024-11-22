package com.sos_assistance.ecommerce.domain.repository;

import com.sos_assistance.ecommerce.domain.IOrderDto;
import com.sos_assistance.ecommerce.domain.model.OrderRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;


public interface OrderRepository extends JpaRepository<OrderRecord, Long> {

    @Query(value = "SELECT " +
            "o.id AS id, " +
            "p.name AS name, " +
            "opq.quantity AS quantity, " +
            "o.status AS status, " +
            "o.created_at AS created, " +
            "o.updated_at AS updated " +
            "FROM orders o " +
            "JOIN order_product_quantity opq ON o.id = opq.order_id " +
            "JOIN product p ON opq.product_id = p.id " +
            "WHERE (:status IS NULL OR :status = '' OR o.status = CAST(:status AS VARCHAR)) " +
            "AND ((CAST(:fromAt AS DATE) IS NULL OR CAST(:toAt AS DATE) IS NULL) OR (DATE(o.created_at) BETWEEN CAST(:fromAt AS DATE) AND CAST(:toAt AS DATE))) " +
            "ORDER BY o.created_at DESC, o.id DESC", nativeQuery = true)
    Page<IOrderDto> getByFilter(@Param("status") String status,
                                @Param("fromAt") LocalDate fromAt,
                                @Param("toAt") LocalDate toAt,
                                Pageable pageable);

    @Query(value = "SELECT " +
            "o.id AS id, " +
            "p.name AS name, " +
            "opq.quantity AS quantity, " +
            "o.status AS status, " +
            "o.created_at AS created, " +
            "o.updated_at AS updated " +
            "FROM orders o " +
            "JOIN order_product_quantity opq ON o.id = opq.order_id " +
            "JOIN product p ON opq.product_id = p.id " +
            "ORDER BY o.created_at DESC, o.id DESC", nativeQuery = true)
    List<IOrderDto> getAll();
}
