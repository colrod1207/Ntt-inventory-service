package org.taller01.inventoryservice.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.taller01.inventoryservice.domain.Order;
import org.taller01.inventoryservice.dto.CreateOrderRequest;
import org.taller01.inventoryservice.dto.StockAddRequest;
import org.taller01.inventoryservice.dto.StockResponse;
import org.taller01.inventoryservice.service.SalesService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SalesController {

    private final SalesService service;

    // PATCH /api/products/{id}/stock/add
    @PatchMapping("/products/{id}/stock/add")
    public StockResponse addStock(@PathVariable String id, @RequestBody @Valid StockAddRequest req) {
        return service.addStock(id, req.amount());
    }

    // POST /api/orders
    @PostMapping("/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public Order createOrder(@RequestBody @Valid CreateOrderRequest req) {
        return service.createOrder(req);
    }
}
