package com.uyghur.java.commandservice.model;

import lombok.*;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class Order {

    private Long orderId;
    private String mealName;
    private int quantity;
    private double price;
    private LocalDate orderDate;
    private String status;
    private int counterNumber;


    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
