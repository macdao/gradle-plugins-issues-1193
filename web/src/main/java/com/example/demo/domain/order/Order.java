package com.example.demo.domain.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Order {
    private final OrderId id;
    private OrderStatus status;
    private String name;
    private int age;

    public Order(OrderId id) {
        this.id = id;
        this.status = OrderStatus.CREATED;
    }

    public void pay() {
        if (status == OrderStatus.CREATED) {
            status = OrderStatus.PAID;
        } else {
            throw new IllegalStateException(status.toString());
        }
    }
}
