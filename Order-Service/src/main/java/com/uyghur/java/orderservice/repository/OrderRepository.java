package com.uyghur.java.orderservice.repository;

import com.uyghur.java.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
