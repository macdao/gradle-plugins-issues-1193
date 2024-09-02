package com.example.demo.adapter.input;

import com.example.demo.domain.order.Order;
import com.example.demo.domain.order.OrderId;
import com.example.demo.domain.order.OrderStatus;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public abstract class WebOrdersBase extends ContractTestBase {
    @BeforeEach
    @Override
    void setUp() {
        super.setUp();
        when(listOrdersQuery.list()).thenReturn(List.of(new Order(new OrderId("order-id-1"), OrderStatus.CREATED, "name", 18)));
        doThrow(new IllegalStateException("PAID")).when(payOrderUseCase).pay(argThat(argument -> argument.orderId().equals("order-id-2")));
        doThrow(new ConstraintViolationException(Collections.emptySet())).when(payOrderUseCase).pay(argThat(argument -> argument.orderId().equals("order-id-3")));
    }
}
