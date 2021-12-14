package com.bank.account.service.impl;

import java.util.Date;
import java.util.List;

import com.bank.account.dtos.Request;
import com.bank.account.service.OperationService;
import com.bank.account.utils.AccountUtils;
import com.bank.account.entities.Operation;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bank.account.entities.Account;
import com.bank.account.exceptions.AccountException;
import com.bank.account.service.AccountService;
import com.bank.account.repository.OperationRepository;

@Service
public class OperationServiceImpl implements OperationService {

	private final AccountService accountService;
	
	private final OperationRepository operationRepository;

	public OperationServiceImpl(AccountService accountService, OperationRepository operationRepository) {
		this.accountService = accountService;
		this.operationRepository = operationRepository;
	}

	@Override
    @Transactional
    public Operation processOperation(Request request, Account account) throws AccountException {
        Operation operation;
        try {
	        operation = new Operation(null, new Date(), request.getOperationAmount(), account.getAccountPosition()
	        		, AccountUtils.determineOperationType(request.getOperationAmount()), account);
	        operationRepository.save(operation);
	        accountService.updateAccountPosition(account);
		} catch (Exception e) {
			throw new AccountException("Operation error : {}", e);
		}
        return operation;
    }

	
	@Override
    @Transactional(readOnly = true)
    public List<Operation> getAllOperationByAccount(Long accountNumber, int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC,"date"));
        return operationRepository.findByAccountAccountNumber(accountNumber, pageRequest);
    }
}
