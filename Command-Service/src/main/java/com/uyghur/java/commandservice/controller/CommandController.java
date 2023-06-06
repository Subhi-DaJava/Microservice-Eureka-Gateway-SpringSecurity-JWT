package com.uyghur.java.commandservice.controller;

import com.uyghur.java.commandservice.model.Order;
import com.uyghur.java.commandservice.service.CommandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/commands")
@RequiredArgsConstructor
@Slf4j
public class CommandController {
    private final CommandService commandService;

    @GetMapping
    public String greetings() {
        return commandService.getGreetings();
    }

    @GetMapping("/{orderId}")
    public Order getOrderDetails(@PathVariable Long orderId) {
        log.debug("getOrderDetails method starts here in CommandController");
        Order order = commandService.getOderDetails(orderId);
        log.info("Order details with the given orderId:{%d} has been successfully retrieved in CommandController".formatted(orderId));
        return order;
    }
}
