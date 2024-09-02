package com.example.demo.application.port.output;

import com.example.demo.domain.product.ProductId;

public interface DeductInventoryPort {
    void deduct(ProductId productId, int quantity);
}
