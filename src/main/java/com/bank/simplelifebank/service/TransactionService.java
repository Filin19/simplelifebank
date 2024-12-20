package com.bank.simplelifebank.service;

import com.bank.simplelifebank.dto.AccountDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TransactionService {

    @Transactional
    AccountDto depositFunds(Long accountId, Double sum);

    @Transactional
    AccountDto withdrawFunds(Long accountId, Double sum);

    @Transactional
    List<AccountDto> transferFunds(Long accountIdrFrom, Long accountIdTo, Double sum);


}
