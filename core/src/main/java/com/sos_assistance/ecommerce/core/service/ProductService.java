package com.sos_assistance.ecommerce.core.service;

import com.sos_assistance.ecommerce.common.constants.ErrorMessages;
import com.sos_assistance.ecommerce.common.ex.SosAssistanceException;
import com.sos_assistance.ecommerce.common.mappers.ProductMapper;
import com.sos_assistance.ecommerce.common.request.ProductRequest;
import com.sos_assistance.ecommerce.common.request.ProductUpdateRequest;
import com.sos_assistance.ecommerce.core.components.I18NComponent;
import com.sos_assistance.ecommerce.domain.model.ProductRecord;
import com.sos_assistance.ecommerce.domain.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductService {
    private final I18NComponent i18NComponent;
    private final ProductRepository repository;

    public ProductService(I18NComponent i18NComponent, ProductRepository repository) {
        this.i18NComponent = i18NComponent;
        this.repository = repository;
    }

    public ProductRecord getById(Long id) {
        log.info("ProductService::getById --id: [{}]", id);
        return repository.findById(id).orElseThrow(() -> new SosAssistanceException(i18NComponent.getMessage(ErrorMessages.PRODUCT_NOT_FOUND, id)));
    }

    public List<ProductRecord> getAll() {
        log.info("ProductService::getAll ");
        return repository.findAll();
    }
    public ProductRecord create(ProductRequest request) {
        log.info("ProductService::create --request: [{}]", request);
        Optional<ProductRecord> productRecord = repository.findByName(request.getName());
        if(productRecord.isPresent()){
            throw new SosAssistanceException(i18NComponent.getMessage(ErrorMessages.PRODUCT_ALREADY_EXIST, request.getName()));
        }
        return repository.save(ProductMapper.mapToCreate(request));
    }


    public ProductRecord update(Long id, ProductUpdateRequest request) {
        log.info("ProductService::update --id: [{}] --request: [{}]", id,request);
        ProductRecord productRecord = getById(id);
        return repository.save(ProductMapper.mapToUpdate(productRecord, request));
    }
}
