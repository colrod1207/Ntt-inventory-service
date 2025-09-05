package org.taller01.inventoryservice.service;

import java.util.Map;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.taller01.inventoryservice.dto.ProductDto;
import org.taller01.inventoryservice.dto.StockResponse;

@Component
public class CatalogClient {

    private final RestClient rest;

    public CatalogClient(RestClient catalogRestClient) {
        this.rest = catalogRestClient;
    }

    public ProductDto getProduct(String id) {
        return rest.get()
                .uri("/api/products/{id}", id)
                .retrieve()
                .body(ProductDto.class);
    }

    public StockResponse adjustStock(String id, int delta) {
        return rest.patch()
                .uri("/api/products/{id}/stock/adjust", id)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Map.of("delta", delta))
                .retrieve()
                .body(StockResponse.class);
    }
}
