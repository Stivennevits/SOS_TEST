package com.sos_assistance.ecommerce.app.rest;

import com.sos_assistance.ecommerce.common.request.ProductRequest;
import com.sos_assistance.ecommerce.common.request.ProductUpdateRequest;
import com.sos_assistance.ecommerce.core.service.ProductService;
import com.sos_assistance.ecommerce.domain.model.ProductRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.sos_assistance.ecommerce.common.router.Router.ProductAPI.*;
import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestController
@RequestMapping(ROOT)
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping(GET_BY_ID)
    @ResponseStatus(OK)
    public ProductRecord getById(@PathVariable Long id) {
        log.info("ProductController::getById --id: [{}]", id);
        return service.getById(id);
    }

    @GetMapping()
    @ResponseStatus(OK)
    public List<ProductRecord> getAll() {
        log.info("ProductController::getAll ");
        return service.getAll();
    }


    @PostMapping
    @ResponseStatus(CREATED)
    public ProductRecord create( @RequestBody ProductRequest request) {
        log.info("ProductController::create --request: [{}]", request);
        return service.create(request);
    }

    @PutMapping(UPDATE)
    @ResponseStatus(NO_CONTENT)
    public ProductRecord update(@PathVariable Long id, @RequestBody ProductUpdateRequest request) {
        log.info("ProductController::update --id: [{}] --request: [{}]", id,request);
        return service.update(id,request);
    }

}
