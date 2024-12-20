package com.bank.simplelifebank.service;

import com.bank.simplelifebank.dto.AccountDto;
import com.bank.simplelifebank.dto.CreateAccountDto;
import com.bank.simplelifebank.entity.Account;
import com.bank.simplelifebank.exception.AccountException;
import com.bank.simplelifebank.mapper.AccountMapper;
import com.bank.simplelifebank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(CreateAccountDto createAccountDto) {
        Account account = AccountMapper.MAPPER.fromCreateDto(createAccountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.MAPPER.toDto(savedAccount);

    }

    @Override
    public AccountDto findAccountByNumber(Long accountId) {
        Account account = accountRepository
                .findById(accountId)
                .orElseThrow(() -> new AccountException("Account not exist"));
        return AccountMapper.MAPPER.toDto(account);
    }

    @Override
    public List<AccountDto> findAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return AccountMapper.MAPPER.toListDto(accounts);
    }


}
