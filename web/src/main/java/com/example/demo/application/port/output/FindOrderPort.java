package com.example.demo.application.port.output;

import com.example.demo.domain.order.Order;
import com.example.demo.domain.order.OrderId;

import java.util.Optional;

public interface FindOrderPort {
    Optional<Order> findById(OrderId orderId);
}
