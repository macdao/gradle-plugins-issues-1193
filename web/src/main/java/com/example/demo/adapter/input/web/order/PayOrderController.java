package com.example.demo.adapter.input.web.order;

import com.example.demo.application.port.input.PayOrderUseCase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
class PayOrderController {
    private final PayOrderUseCase payOrderUseCase;

    @PutMapping("/orders/{orderId}/pay")
    PayOrderResponse payOrder(@RequestBody @Valid PayOrderRequest request, @PathVariable String orderId) {
        PayOrderUseCase.PayOrderCommand command = new PayOrderUseCase.PayOrderCommand(orderId, request.amount);
        payOrderUseCase.pay(command);
        return new PayOrderResponse(orderId);
    }

    record PayOrderRequest(@NotNull BigDecimal amount) {
    }

    record PayOrderResponse(String id) {
    }
}
