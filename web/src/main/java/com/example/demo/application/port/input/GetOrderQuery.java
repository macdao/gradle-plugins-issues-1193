package com.example.demo.application.port.input;

import com.example.demo.domain.order.Order;

public interface GetOrderQuery {
    Order findById();
}
