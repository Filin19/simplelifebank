package com.bank.simplelifebank.service;

import com.bank.simplelifebank.dto.AccountDto;
import com.bank.simplelifebank.entity.Account;
import com.bank.simplelifebank.exception.AccountException;
import com.bank.simplelifebank.exception.NotEnoughFoundsException;
import com.bank.simplelifebank.mapper.AccountMapper;
import com.bank.simplelifebank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;

    @Autowired
    public TransactionServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto depositFunds(Long accountId, Double amount) {
        Account account = accountRepository
                .findById(accountId)
                .orElseThrow(() -> new AccountException("Account not exist"));
        account.setBalance(account.getBalance() + amount);
        Account updatedAccount = accountRepository.save(account);

        return AccountMapper.MAPPER.toDto(updatedAccount);
    }

    @Override
    public AccountDto withdrawFunds(Long accountId, Double amount) {
        Account account = accountRepository
                .findById(accountId)
                .orElseThrow(() -> new AccountException("Account not exist"));

        if (account.getBalance() < amount) {
            throw new NotEnoughFoundsException("not enough funds on account");
        }

        double totalFunds = account.getBalance() - amount;
        account.setBalance(totalFunds);
        Account updatedAccount = accountRepository.save(account);

        return AccountMapper.MAPPER.toDto(updatedAccount);
    }

    @Override
    public List<AccountDto> transferFunds(Long accountIdFrom, Long accountIdTo, Double amount) {
        Account accountFromTransfer = accountRepository
                .findById(accountIdFrom)
                .orElseThrow(() -> new AccountException("Account not exist"));

        if (accountFromTransfer.getBalance() < amount) {
            throw new NotEnoughFoundsException("not enough funds for transfer");
        }

        Account accountToTransfer = accountRepository
                .findById(accountIdTo)
                .orElseThrow(() -> new AccountException("Account not exist"));

        double totalFunds = accountFromTransfer.getBalance() - amount;
        accountFromTransfer.setBalance(totalFunds);
        accountToTransfer.setBalance(accountToTransfer.getBalance() + amount);
        List<Account> accounts = List.of(accountFromTransfer, accountToTransfer);
        List<Account> updatedAccounts = accountRepository.saveAll(accounts);

        return AccountMapper.MAPPER.toListDto(updatedAccounts);
    }
}
