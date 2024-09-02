package com.example.demo.adapter.input;

import com.example.demo.application.port.input.ListOrdersQuery;
import com.example.demo.application.port.input.PayOrderUseCase;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
abstract class ContractTestBase {
    @LocalServerPort
    int port;

    @MockBean
    ListOrdersQuery listOrdersQuery;

    @MockBean
    PayOrderUseCase payOrderUseCase;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }
}
