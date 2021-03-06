package com.service.account.exceptionhandler;

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
                new HttpClientErrorException(HttpStatus.NOT_FOUND, "Customer not found");

        ResponseEntity responseEntity = controllerExceptionHandler.handleHttpClientErrorException(httpClientErrorException);

        assertEquals(404, responseEntity.getStatusCodeValue());
        assertEquals("Customer not found", responseEntity.getBody());
    }

    @Test
    void handleNumberFormatException_shouldReturn400AndBadRequestErrorMessage_whenANonIntNumberIsPassed() {
        NumberFormatException numberFormatException =
                new NumberFormatException("Bad Request, invalid input");

        ResponseEntity responseEntity = controllerExceptionHandler.handleNumberFormatException(numberFormatException);

        assertEquals(400, responseEntity.getStatusCodeValue());
        assertEquals("Bad Request, invalid input", responseEntity.getBody());
    }

    @Test
    void handleHttpClientErrorException_shouldReturn404AndAccountErrorMessage_whenHttpClientErrorExceptionWith404Occurs() {
        HttpClientErrorException httpClientErrorException =
                new HttpClientErrorException(HttpStatus.NOT_FOUND, "Account Number not found");

        ResponseEntity responseEntity = controllerExceptionHandler.handleHttpClientErrorException(httpClientErrorException);

        assertEquals(404, responseEntity.getStatusCodeValue());
        assertEquals("Account Number not found", responseEntity.getBody());
    }
}
