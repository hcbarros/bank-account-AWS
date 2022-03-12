package br.com.bankaccountapi;

import br.com.bankaccountapi.models.Account;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.junit.jupiter.api.Order;
import org.springframework.http.ResponseEntity;


    @TestMethodOrder(OrderAnnotation.class)
    @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
    class BankAccountApiApplicationTests {

        @LocalServerPort
        private int port;

        @Autowired
        private TestRestTemplate restTemplate;



    }
