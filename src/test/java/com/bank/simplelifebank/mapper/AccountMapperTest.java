package com.bank.simplelifebank.mapper;

import com.bank.simplelifebank.dto.AccountDto;
import com.bank.simplelifebank.dto.CreateAccountDto;
import com.bank.simplelifebank.entity.Account;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AccountMapperTest {

    @Test
    void testFromDto() {
        AccountDto accountDto = new AccountDto("Lurr", 800);
        Account result = AccountMapper.MAPPER.fromDto(accountDto);

        assertEquals("Lurr", result.getAccountOwnerName());
        assertEquals(800, result.getBalance());
    }

    @Test
    void testFromCreationDto() {
        CreateAccountDto createAccountDto = new CreateAccountDto("Lurr", 800);
        Account result = AccountMapper.MAPPER.fromCreateDto(createAccountDto);

        assertEquals("Lurr", result.getAccountOwnerName());
        assertEquals(800, result.getBalance());
    }

    @Test
    void testToDto() {
        Account account = new Account(56L, "Ndnd", 500);
        AccountDto result = AccountMapper.MAPPER.toDto(account);

        assertEquals("Ndnd", result.getAccountOwnerName());
        assertEquals(500, result.getBalance());
    }

    @Test
    void testToListDto() {
        List<Account> accounts = List.of(new Account(55L, "Lurr", 800),
                new Account(56L, "Ndnd", 500));
        List<AccountDto> result = AccountMapper.MAPPER.toListDto(accounts);

        assertEquals("Lurr", result.getFirst().getAccountOwnerName());
        assertEquals(800, result.getFirst().getBalance());
        assertEquals("Ndnd", result.get(1).getAccountOwnerName());
        assertEquals(500, result.get(1).getBalance());
    }
}