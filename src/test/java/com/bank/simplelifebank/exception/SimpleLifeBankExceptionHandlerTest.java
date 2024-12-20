package com.bank.simplelifebank.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class SimpleLifeBankExceptionHandlerTest {

    @Mock
    private WebRequest webRequest;

    @Spy
    @InjectMocks
    private SimpleLifeBankExceptionHandler simpleLifeBankExceptionHandler;

    @Test
    void testHandleAccountException() {
        AccountException exception = new AccountException("account not exist");
        doReturn("details").when(webRequest).getDescription(false);
        ResponseEntity<ErrorDetails> result = simpleLifeBankExceptionHandler.handleAccountException(exception, webRequest);

        assertEquals( "details", Objects.requireNonNull(result.getBody()).getDetail());
        assertEquals( "ACCOUNT_NOT_FOUND", Objects.requireNonNull(result.getBody()).getErrorCode());
        assertEquals( "account not exist", Objects.requireNonNull(result.getBody()).getMessage());
    }

    @Test
    void testHandleNotEnoughFoundsException() {
        NotEnoughFoundsException exception = new NotEnoughFoundsException("not enough founds");
        doReturn("details").when(webRequest).getDescription(false);
        ResponseEntity<ErrorDetails> result = simpleLifeBankExceptionHandler.handleNotEnoughFoundsException(exception, webRequest);

        assertEquals( "details", Objects.requireNonNull(result.getBody()).getDetail());
        assertEquals( "NOT_ENOUGH_FOUNDS", Objects.requireNonNull(result.getBody()).getErrorCode());
        assertEquals( "not enough founds", Objects.requireNonNull(result.getBody()).getMessage());
    }

    @Test
    void testHandleException() {
        Exception exception = new Exception("error");
        doReturn("details").when(webRequest).getDescription(false);
        ResponseEntity<ErrorDetails> result = simpleLifeBankExceptionHandler.handleException(exception, webRequest);

        assertEquals( "details", Objects.requireNonNull(result.getBody()).getDetail());
        assertEquals( "INTERNAL_SERVER_ERROR", Objects.requireNonNull(result.getBody()).getErrorCode());
        assertEquals( "error", Objects.requireNonNull(result.getBody()).getMessage());
    }
}