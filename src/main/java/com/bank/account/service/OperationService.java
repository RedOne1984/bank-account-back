package com.bank.account.service;

import java.util.List;

import com.bank.account.dtos.Request;
import com.bank.account.entities.Account;
import com.bank.account.entities.Operation;
import com.bank.account.exceptions.AccountException;

public interface OperationService {
	List<Operation> getAllOperationByAccount(Long accountNumber, int page, int size);

	Operation processOperation(Request request, Account account) throws AccountException;
}
