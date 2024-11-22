package com.sos_assistance.ecommerce.domain.repository;

import com.sos_assistance.ecommerce.domain.model.ProductRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ProductRepository extends JpaRepository<ProductRecord, Long> {
    Optional<ProductRecord> findByName(String name);
}
