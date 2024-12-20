package com.bank.simplelifebank.service;

import com.bank.simplelifebank.entity.Account;
import com.bank.simplelifebank.exception.AccountException;
import com.bank.simplelifebank.exception.NotEnoughFoundsException;
import com.bank.simplelifebank.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @Spy
    private Account account;

    @Spy
    private Account account2;

    @Spy
    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Test
    void testDepositFunds() {
        doReturn(Optional.of(account)).when(accountRepository).findById(anyLong());
        doReturn(300.00).when(account).getBalance();
        transactionService.depositFunds(55L, 800.83);

        verify(account).setBalance(1100.83);
        verify(accountRepository).save(any(Account.class));
    }

    @Test
    void testDepositFundsWhenAccountNotFound() {
        assertThrows(AccountException.class, () -> {
            transactionService.depositFunds(55L, 800.83);
        });
    }

    @Test
    void testWithdrawFunds() {
        doReturn(Optional.of(account)).when(accountRepository).findById(anyLong());
        doReturn(800.00).when(account).getBalance();
        transactionService.withdrawFunds(55L, 400.00);

        verify(account).setBalance(400);
        verify(accountRepository).save(any(Account.class));
    }

    @Test
    void testWithdrawFundsWhenAccountNotFound() {
        assertThrows(AccountException.class, () -> {
            transactionService.withdrawFunds(55L, 400.00);
        });
    }

    @Test
    void testWithdrawWhenNotEnoughFounds() {
        doReturn(Optional.of(account)).when(accountRepository).findById(anyLong());
        doReturn(300.00).when(account).getBalance();

        assertThrows(NotEnoughFoundsException.class, () -> {
            transactionService.withdrawFunds(55L, 80000.00);
        });
    }

    @Test
    void testTransferFunds() {
        doReturn(Optional.of(account)).when(accountRepository).findById(55L);
        doReturn(Optional.of(account2)).when(accountRepository).findById(78L);
        doReturn(800.00).when(account).getBalance();
        doReturn(400.00).when(account2).getBalance();
        transactionService.transferFunds(55L, 78L, 400.00);

        verify(account).setBalance(400);
        verify(account2).setBalance(800);
        verify(accountRepository).saveAll(anyList());
    }

    @Test
    void testTransferWhenNotEnoughFounds() {
        doReturn(Optional.of(account)).when(accountRepository).findById(55L);
        doReturn(Optional.of(account2)).when(accountRepository).findById(78L);
        doReturn(800.00).when(account).getBalance();
        doReturn(400.00).when(account2).getBalance();
        transactionService.transferFunds(55L, 78L, 400.00);

        assertThrows(NotEnoughFoundsException.class, () -> {
            transactionService.transferFunds(55L, 78L, 80000.00);
        });
    }

    @Test
    void testTransferWhenAccountNotFound() {
        assertThrows(AccountException.class, () -> {
            transactionService.transferFunds(55L, 78L, 400.00);
        });
    }
}