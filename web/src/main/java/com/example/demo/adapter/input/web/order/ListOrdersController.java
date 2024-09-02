package com.example.demo.adapter.input.web.order;

import com.example.demo.application.port.input.ListOrdersQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
class ListOrdersController {
    private final ListOrdersQuery listOrdersQuery;

    @GetMapping()
    List<OrderResponse> list() {
        return listOrdersQuery.list().stream()
                .map(order -> new OrderResponse(order.getId().value(), order.getStatus().toString()))
                .toList();
    }

    record OrderResponse(String id, String status) {
    }
}
