package org.taller01.inventoryservice.domain;

import java.math.BigDecimal;
import java.time.Instant;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("orders")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Order {
    @Id
    private String id;

    private String status;      // "CREATED"
    private String productId;
    private int quantity;

    private BigDecimal unitPrice;
    private BigDecimal total;

    private Instant createdAt;
}
