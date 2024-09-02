package com.example.demo.application;

import com.example.demo.application.port.input.ListOrdersQuery;
import com.example.demo.domain.order.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListOrdersService implements ListOrdersQuery {
    @Override
    public List<Order> list() {
        return List.of();
    }
}
