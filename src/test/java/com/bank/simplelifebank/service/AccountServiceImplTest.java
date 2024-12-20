package com.bank.simplelifebank.service;

import com.bank.simplelifebank.dto.AccountDto;
import com.bank.simplelifebank.dto.CreateAccountDto;
import com.bank.simplelifebank.entity.Account;
import com.bank.simplelifebank.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @Spy
    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    void testCreateAccount() {
        CreateAccountDto createAccountDto = new CreateAccountDto("Lurr", 800);
        accountService.createAccount(createAccountDto);
        verify(accountRepository).save(any(Account.class));
    }

    @Test
    void testFindAccountByNumber() {
        Account account = new Account(76L, "Lurr", 800);
        doReturn(Optional.of(account)).when(accountRepository).findById(anyLong());
        AccountDto result = accountService.findAccountByNumber(4585L);

        assertEquals("Lurr", result.getAccountOwnerName());
        assertEquals(800, result.getBalance());
    }

    @Test
    void testFindAllAccounts() {
        List<Account> accounts = List.of(new Account(55L, "Lurr", 564),
                new Account(98L, "Ndnd", 155.86));
        doReturn(accounts).when(accountRepository).findAll();
        List<AccountDto> result = accountService.findAllAccounts();

        assertEquals(564, result.get(0).getBalance());
        assertEquals("Lurr", result.get(0).getAccountOwnerName());
        assertEquals(155.86, result.get(1).getBalance());
        assertEquals("Ndnd", result.get(1).getAccountOwnerName());
    }
}