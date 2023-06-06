package com.uyghur.java.commandservice.service;

import com.uyghur.java.commandservice.model.Order;

public interface CommandService {
    String getGreetings();

    Order getOderDetails(Long orderId);
}
