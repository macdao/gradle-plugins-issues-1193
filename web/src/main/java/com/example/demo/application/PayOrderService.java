package com.example.demo.application;

import com.example.demo.application.port.input.OrderNotFoundException;
import com.example.demo.application.port.input.PayOrderUseCase;
import com.example.demo.application.port.output.FindOrderPort;
import com.example.demo.application.port.output.SaveOrderPort;
import com.example.demo.domain.order.Order;
import com.example.demo.domain.order.OrderId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@Service
class PayOrderService implements PayOrderUseCase {
    private final FindOrderPort findOrderPort;
    private final SaveOrderPort saveOrderPort;
    private final TransactionTemplate transactionTemplate;

    public PayOrderService(FindOrderPort findOrderPort, SaveOrderPort saveOrderPort, PlatformTransactionManager transactionManager) {
        this.findOrderPort = findOrderPort;
        this.saveOrderPort = saveOrderPort;
        this.transactionTemplate = new TransactionTemplate(transactionManager);
    }

    @Override
    @Transactional
    public void pay(PayOrderCommand command) {
        Order order = findOrderPort.findById(new OrderId(command.orderId()))
                .orElseThrow(OrderNotFoundException::new);
        order.pay();
        saveOrderPort.save(order);
    }

    public void pay2(PayOrderCommand command) {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            public void doInTransactionWithoutResult(TransactionStatus status) {
                Order order = findOrderPort.findById(new OrderId(command.orderId()))
                        .orElseThrow(OrderNotFoundException::new);
                order.pay();
                saveOrderPort.save(order);
            }
        });
    }
}
