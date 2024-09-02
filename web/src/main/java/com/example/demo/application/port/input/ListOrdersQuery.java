package com.example.demo.application.port.input;

import com.example.demo.domain.order.Order;

import java.util.List;

public interface ListOrdersQuery {
    List<Order> list();
}
