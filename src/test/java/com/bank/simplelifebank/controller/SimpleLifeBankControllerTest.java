package com.bank.simplelifebank.controller;

import com.bank.simplelifebank.dto.AccountDto;
import com.bank.simplelifebank.dto.CreateAccountDto;
import com.bank.simplelifebank.service.AccountService;
import com.bank.simplelifebank.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SimpleLifeBankControllerTest {

    @Mock
    private AccountService accountService;

    @Mock
    private TransactionService transactionService;

    @Spy
    @InjectMocks
    private SimpleLifeBankController controller;

    @Test
    void testCreateAccount() {
        CreateAccountDto createAccountDto = new CreateAccountDto("Lurr", 800);
        ResponseEntity<AccountDto> result = controller.createAccount(createAccountDto);

        verify(controller).createAccount(any(CreateAccountDto.class));
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }

    @Test
    void testGetAccountByNumber() {
        AccountDto accountDto = new AccountDto("Lurr", 800);
        doReturn(accountDto).when(accountService).findAccountByNumber(anyLong());
        ResponseEntity<AccountDto> result = controller.getAccountByNumber(22L);

        verify(accountService).findAccountByNumber(anyLong());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Lurr", Objects.requireNonNull(result.getBody()).getAccountOwnerName());
        assertEquals(800, result.getBody().getBalance());
    }

    @Test
    void testGetAllAccounts() {
        List<AccountDto> accounts = List.of(new AccountDto("Lurr", 800),
                new AccountDto("Ndnd", 1100));
        doReturn(accounts).when(accountService).findAllAccounts();
        ResponseEntity<List<AccountDto>> result = controller.getAllAccounts();

        verify(accountService).findAllAccounts();
        assertEquals(800, Objects.requireNonNull(result.getBody()).get(0).getBalance());
        assertEquals(1100, Objects.requireNonNull(result.getBody()).get(1).getBalance());
    }

    @Test
    void testDepositFunds() {
        ResponseEntity<AccountDto> result = controller.depositFunds(55L, 800.00);

        verify(transactionService).depositFunds(anyLong(), anyDouble());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void testWithdrawFunds() {
        ResponseEntity<AccountDto> result = controller.withdrawFunds(55L, 800.00);

        verify(transactionService).withdrawFunds(anyLong(), anyDouble());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void testTransferFunds() {
        ResponseEntity<List<AccountDto>> result = controller.transferFunds(55L, 78L, 800.00);

        verify(transactionService).transferFunds(anyLong(), anyLong(), anyDouble());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
}