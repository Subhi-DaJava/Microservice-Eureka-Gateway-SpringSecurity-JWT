package com.uyghur.java.commandservice.service.impl;

import com.uyghur.java.commandservice.model.Order;
import com.uyghur.java.commandservice.service.CommandService;
import com.uyghur.java.commandservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommandServiceImpl implements CommandService {

    private final OrderService orderService;

    @Override
    public String getGreetings() {
        return "Welcome to Your Command Service";
    }

    @Override
    public Order getOderDetails(Long orderId) {
        log.debug("getOrderDetails method in CommandServiceImpl in Command-Service");
        Order orderDetails = orderService.retrieveOrderDetails(orderId);
        log.info("Order with the given orderId:{%d} has been successfully retrieved from OrderService in Command-Service");
        return orderDetails;
    }
}
