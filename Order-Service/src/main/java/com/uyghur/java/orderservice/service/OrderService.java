package com.uyghur.java.orderservice.service;

import com.uyghur.java.orderservice.model.Order;
import com.uyghur.java.orderservice.model.OrderDTO;

public interface OrderService {
    String greetings();
    Order getOrder(Long orderId);
    OrderDTO makeOrder(OrderDTO orderDTO);
}
