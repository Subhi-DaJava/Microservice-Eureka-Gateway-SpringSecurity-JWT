package com.uyghur.java.commandservice.service;

import com.uyghur.java.commandservice.model.Order;

public interface OrderService {
    Order retrieveOrderDetails(Long orderId);
}
