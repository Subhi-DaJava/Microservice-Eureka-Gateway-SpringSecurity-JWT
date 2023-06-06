package com.uyghur.java.orderservice.controller;

import com.uyghur.java.orderservice.model.Order;
import com.uyghur.java.orderservice.model.OrderDTO;
import com.uyghur.java.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public String greetings() {
        return orderService.greetings();
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long orderId) {
        log.debug("getOrderById method in OrderController starts here");
        Order order = orderService.getOrder(orderId);
        log.info("Order has been successfully retrieved by orderId:{%d}".formatted(orderId));
        return ResponseEntity.ok(order);
    }

    @PostMapping("/")
    public ResponseEntity<OrderDTO> makeOrder(@RequestBody @Valid OrderDTO orderDTO) {
        log.debug("makerOrder method in OrderController starts here");
        OrderDTO orderReturned = orderService.makeOrder(orderDTO);
        log.info("New order with orderId:{%d} has been successfully saved in database, in OrderController".formatted(orderReturned.getOrderId()));
        return new ResponseEntity<>(orderReturned, HttpStatus.CREATED);
    }
}
