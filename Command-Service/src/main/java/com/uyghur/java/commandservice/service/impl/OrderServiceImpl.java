package com.uyghur.java.commandservice.service.impl;

import com.uyghur.java.commandservice.exception.OrderNotFoundException;
import com.uyghur.java.commandservice.model.Order;
import com.uyghur.java.commandservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final RestTemplate restTemplate;
    @Override
    public Order retrieveOrderDetails(Long orderId) {
        log.debug("retrieveOrderDetail method starts here in OrderServiceImpl in Command-Service");
        Order order = restTemplate.getForObject("http://ORDER-SERVICE/orders/" + orderId, Order.class);
        if(order == null) {
            log.debug("No order found with the given orderId:{%d}".formatted(orderId));
            throw new OrderNotFoundException("Order not found with given orderId:{%d}".formatted(orderId));
        }
        log.info("Order with the orderId:{%d} has been successfully retrieved from OrderService in OrderServiceImpl in Command-Service");
        return order;
    }
}
