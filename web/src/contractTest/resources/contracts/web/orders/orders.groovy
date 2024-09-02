import static org.springframework.cloud.contract.spec.Contract.make

[make {
    name 'list orders'
    request {
        method 'GET'
        url '/api/orders'
    }
    response {
        status OK()
        body([[id: "order-id-1", status: "CREATED"]])
        headers {
            contentType('application/json')
        }
    }
}]