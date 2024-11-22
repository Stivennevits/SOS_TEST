package com.sos_assistance.ecommerce.app.rest;

import com.sos_assistance.ecommerce.common.request.OrderRequest;
import com.sos_assistance.ecommerce.core.service.OrderService;
import com.sos_assistance.ecommerce.domain.IOrderDto;
import com.sos_assistance.ecommerce.domain.model.OrderRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static com.sos_assistance.ecommerce.common.router.Router.OrderAPI.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;


@Slf4j
@RestController
@RequestMapping(ROOT)
public class OrderController {
    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }


    @GetMapping(GET_ALL_PAGING)
    @ResponseStatus(OK)
    public Page<IOrderDto> getByFilter(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromAt,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toAt,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        log.info("OrderController::getByFilter --status: [{}] --fromAt: [{}] --toAt: [{}] --page: [{}] --size: [{}]", status, fromAt, toAt, page, size);
        return service.getByFilter(status, fromAt, toAt, PageRequest.of(page, size));
    }

    @GetMapping(GET_ALL)
    @ResponseStatus(OK)
    public List<IOrderDto> getAll() {
        log.info("OrderController::getAll ");
        return service.getAll();
    }


    @GetMapping(GET_BY_ID)
    @ResponseStatus(OK)
    public OrderRecord getById(@PathVariable Long id){
        log.info("OrderController::getById --id: [{}] ", id);
        return service.getById(id);
    }


    @PostMapping
    @ResponseStatus(CREATED)
    public void create(@RequestBody OrderRequest request) {
        log.info("OrderController::create --request: [{}]", request);
        service.create(request);
    }

    @PutMapping(UPDATE)
    @ResponseStatus(OK)
    public void updateStatus(@PathVariable Long id, @RequestParam String status){
        log.info("OrderController::updateStatus --id: [{}] --status: [{}]", id,status);
        service.updateStatus(id,status);
    }

    @DeleteMapping(DELETE)
    @ResponseStatus(OK)
    public void softDelete(@PathVariable Long id){
        log.info("OrderController::softDelete --id: [{}]", id);
        service.softDelete(id);
    }


}
