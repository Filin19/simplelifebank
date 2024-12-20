package com.bank.simplelifebank.service;

import com.bank.simplelifebank.dto.AccountDto;
import com.bank.simplelifebank.dto.CreateAccountDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AccountService {

    @Transactional
    AccountDto createAccount(CreateAccountDto accountDto);

    @Transactional
    AccountDto findAccountByNumber(Long accountId);

    @Transactional
    List<AccountDto> findAllAccounts();

}
