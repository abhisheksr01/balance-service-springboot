package com.service.cashier.exceptionhandler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ControllerExceptionHandlerTest {
    private ControllerExceptionHandler controllerExceptionHandler;

    @BeforeEach
    void setUp() {
        controllerExceptionHandler = new ControllerExceptionHandler();
    }

    @Test
    void handleHttpClientErrorException_shouldReturn404AndErrorMessage_whenHttpClientErrorExceptionWith404Occurs() {
        HttpClientErrorException httpClientErrorException =
                new HttpClientErrorException(HttpStatus.NOT_FOUND,
                        "Account Number not found, Unable to process the transaction");

        ResponseEntity responseEntity = controllerExceptionHandler.
                handleHttpClientErrorException(httpClientErrorException);

        assertEquals(404, responseEntity.getStatusCodeValue());
        assertEquals("Account Number not found, Unable to process the transaction", responseEntity.getBody());
    }
}
