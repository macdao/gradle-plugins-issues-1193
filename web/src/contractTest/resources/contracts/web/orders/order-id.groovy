import static org.springframework.cloud.contract.spec.Contract.make

[make {
    name 'pay order'
    request {
        method 'PUT'
        url '/api/orders/order-id-1/pay'
        body([
                amount: 100
        ])
        headers {
            contentType('application/json')
        }
    }
    response {
        status OK()
        body([
                id: "order-id-1"
        ])
        headers {
            contentType('application/json')
        }
    }
}, make {
    name 'pay order failed due to paid'
    request {
        method 'PUT'
        url '/api/orders/order-id-2/pay'
        body([
                amount: 100
        ])
        headers {
            contentType('application/json')
        }
    }
    response {
        status CONFLICT()
    }
}, make {
    name 'pay order failed due to no amount'
    request {
        method 'PUT'
        url '/api/orders/order-id-1/pay'
        body([:])
        headers {
            contentType('application/json')
        }
    }
    response {
        status BAD_REQUEST()
    }
}, make {
    name 'pay order failed due to ConstraintViolationException'
    request {
        method 'PUT'
        url '/api/orders/order-id-3/pay'
        headers {
            contentType('application/json')
        }
        body(amount: 100)
    }
    response {
        status BAD_REQUEST()
    }
}]