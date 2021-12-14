package com.bank.account.service;

import com.bank.account.entities.Account;

public interface AccountService {
	Account updateAccountPosition(Account account);

	Account findAccountByAccountNumber(Long accountNumber);
}
