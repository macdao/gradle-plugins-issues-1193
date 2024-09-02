package com.example.demo.application.port.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

@Validated
public interface PayOrderUseCase {
    void pay(@Valid PayOrderCommand command);

    record PayOrderCommand(@NotNull String orderId, BigDecimal amount) {
    }
}
