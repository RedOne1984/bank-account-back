package com.bank.account.service.impl;

import com.bank.account.entities.Account;
import com.bank.account.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bank.account.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional
    public Account updateAccountPosition(Account account) {
        return accountRepository.save(account);
    }

    @Override
    @Transactional(readOnly = true)
    public Account findAccountByAccountNumber(Long accountNumber) {
        return accountRepository.getAccountByAccountNumber(accountNumber);
    }
}
