package com.example.demo.adapter.input;

import com.example.demo.domain.order.Order;
import com.example.demo.domain.order.OrderId;
import com.example.demo.domain.order.OrderStatus;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import static org.mockito.Mockito.when;

public abstract class WebProductsBase extends ContractTestBase {
    @BeforeEach
    @Override
    void setUp() {
        super.setUp();
        when(listOrdersQuery.list()).thenReturn(List.of(new Order(new OrderId("order-id-1"), OrderStatus.CREATED, "name", 18)));
    }
}
