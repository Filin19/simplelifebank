package com.bank.simplelifebank.controller;

import com.bank.simplelifebank.dto.AccountDto;
import com.bank.simplelifebank.dto.CreateAccountDto;
import com.bank.simplelifebank.service.AccountService;
import com.bank.simplelifebank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/bank")
public class SimpleLifeBankController {

    private final AccountService accountService;
    private final TransactionService transactionService;

    @Autowired
    public SimpleLifeBankController(AccountService accountService, TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    @PostMapping(value = "accounts/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDto> createAccount(@RequestBody CreateAccountDto createAccountDto) {
        if (createAccountDto == null || createAccountDto.getAccountOwnerName().isEmpty() && createAccountDto.getBalance() == 0) {
            throw new IllegalArgumentException("Account is empty");
        }

        AccountDto savedAccount = accountService.createAccount(createAccountDto);
        return new ResponseEntity<>(savedAccount, HttpStatus.CREATED);

    }

    @GetMapping(value = "accounts/{accountId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDto> getAccountByNumber(@PathVariable Long accountId) {
        AccountDto accountDto = accountService.findAccountByNumber(accountId);
        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }

    @GetMapping(value = "accounts/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        List<AccountDto> accountDto = accountService.findAllAccounts();
        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }

    @PutMapping(value = "accounts/{accountId}/deposit")
    public ResponseEntity<AccountDto> depositFunds(@PathVariable Long accountId, @RequestBody Double amount) {
        AccountDto updatedAccount = transactionService.depositFunds(accountId, amount);
        return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
    }

    @PutMapping(value = "accounts/{accountId}/withdraw", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDto> withdrawFunds(@PathVariable Long accountId, @RequestBody Double amount) {
        AccountDto updatedAccount = transactionService.withdrawFunds(accountId, amount);
        return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
    }

    @PutMapping(value = "accounts/{accountIdFrom}/transfer/{accountIdTo}")
    public ResponseEntity<List<AccountDto>> transferFunds(@PathVariable Long accountIdFrom, @PathVariable Long accountIdTo, @RequestBody Double amount) {
        List<AccountDto> updatedAccounts = transactionService.transferFunds(accountIdFrom, accountIdTo, amount);
        return new ResponseEntity<>(updatedAccounts, HttpStatus.OK);
    }

}
