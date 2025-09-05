package org.taller01.inventoryservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.taller01.inventoryservice.domain.Order;

public interface OrderRepository extends MongoRepository<Order, String> {}
