package org.taller01.inventoryservice.dto;

import java.math.BigDecimal;

// Solo lo que usamos del catálogo
public record ProductDto(String id, BigDecimal price) {}
