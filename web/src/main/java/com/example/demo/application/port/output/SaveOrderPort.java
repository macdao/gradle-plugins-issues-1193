package com.example.demo.application.port.output;

import com.example.demo.domain.order.Order;

public interface SaveOrderPort {
    void save(Order order);
}
