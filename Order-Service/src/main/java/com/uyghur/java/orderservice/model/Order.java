package com.uyghur.java.orderservice.model;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Entity
@Table(name = "orders")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    @NotNull(message = "mealName is mandatory")
    private String mealName;
    @NotNull(message = "Quantity is mandatory")
    private int quantity;
    @NotNull(message = "Price is mandatory")
    private double price;
    private LocalDate orderDate;
    private String status;
    private int counterNumber;

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
