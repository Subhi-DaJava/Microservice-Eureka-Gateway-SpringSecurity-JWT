package com.uyghur.java.orderservice.service;

import com.uyghur.java.orderservice.exception.OrderNotFoundException;
import com.uyghur.java.orderservice.model.Order;
import com.uyghur.java.orderservice.model.OrderDTO;
import com.uyghur.java.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    @Override
    public String greetings() {
        return "Welcome to our Order Service!";
    }

    @Override
    public Order getOrder(Long orderId) {
        log.debug("getOrder method starts here in OrderServiceImpl in ORDER-SERVICE");
        Order order = repository.findById(orderId).orElseThrow(() -> new OrderNotFoundException("Order not found with given orderId:{%d}".formatted(orderId)));
        log.info("Order with the orderId:{%d} has been successfully retrieved from database in OrderServiceImpl in Order-Service");
        return order;
    }

    @Override
    public OrderDTO makeOrder(OrderDTO orderDTO) {
        log.debug("makeOrder method starts here in OrderServiceImpl, in ORDER-SERVICE");
        Order order = new Order();
        order.setOrderDate(LocalDate.now());
        order.setPrice(orderDTO.getPrice());
        order.setQuantity(orderDTO.getQuantity());
        order.setStatus(orderDTO.getStatus());
        order.setMealName(orderDTO.getMealName());
        order.setCounterNumber(orderDTO.getCounterNumber());

        Order orderSaved = repository.save(order);
        OrderDTO oderReturned = new OrderDTO();
        BeanUtils.copyProperties(orderSaved, oderReturned);
        log.info("Order has been successfully saved with orderId:{%d} in OrderServiceImpl, in ORDER-SERVICE".formatted(orderSaved.getOrderId()));

        return oderReturned;
    }
}
