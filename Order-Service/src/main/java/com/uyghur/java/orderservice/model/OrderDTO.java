package com.uyghur.java.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long orderId;
    @NotNull(message = "mealName is mandatory")
    private String mealName;
    @NotNull(message = "Quantity is mandatory")
    private int quantity;
    @NotNull(message = "Price is mandatory")
    private double price;
    private String status;
    private LocalDate orderDate;
    private int counterNumber;
}
