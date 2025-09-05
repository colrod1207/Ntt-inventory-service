package org.taller01.inventoryservice.dto;

import jakarta.validation.constraints.Min;

public record StockAddRequest(@Min(1) int amount) {}
