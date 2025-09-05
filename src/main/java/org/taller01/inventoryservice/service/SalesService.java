package org.taller01.inventoryservice.service;

import java.math.BigDecimal;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.taller01.inventoryservice.domain.Order;
import org.taller01.inventoryservice.dto.CreateOrderRequest;
import org.taller01.inventoryservice.dto.ProductDto;
import org.taller01.inventoryservice.dto.StockResponse;
import org.taller01.inventoryservice.repository.OrderRepository;

@Service
@RequiredArgsConstructor
public class SalesService {

    private final CatalogClient catalog;
    private final OrderRepository orders;

    /** PATCH /api/products/{id}/stock/add */
    @Transactional
    public StockResponse addStock(String productId, int amount) {
        ProductDto product = catalog.getProduct(productId);
        if (product == null) {
            throw new IllegalArgumentException("Product not found: " + productId);
        }
        // delta positivo => suma stock
        return catalog.adjustStock(productId, amount);
    }

    @Transactional
    public Order createOrder(CreateOrderRequest req) {
        // 1) valida producto y toma precio
        ProductDto product = catalog.getProduct(req.productId());
        if (product == null) {
            throw new IllegalArgumentException("Product not found: " + req.productId());
        }

        // 2) descuenta stock en catálogo (lanza si no hay suficiente)
        catalog.adjustStock(req.productId(), -req.quantity());

        // 3) persiste la orden
        BigDecimal unitPrice = product.price();
        BigDecimal total = unitPrice.multiply(BigDecimal.valueOf(req.quantity()));

        return orders.save(Order.builder()
                .status("CREATED")
                .productId(req.productId())
                .quantity(req.quantity())
                .unitPrice(unitPrice)
                .total(total)
                .createdAt(Instant.now())
                .build());
    }
}
