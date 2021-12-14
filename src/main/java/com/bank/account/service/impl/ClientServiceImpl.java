package com.bank.account.service.impl;

import com.bank.account.entities.Client;
import com.bank.account.service.ClientService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bank.account.security.CustomUserDetail;
import com.bank.account.repository.ClientRepository;

@Service
public class ClientServiceImpl implements UserDetailsService, ClientService {

	private final ClientRepository clientRepository;

	public ClientServiceImpl(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String usernameOrEmail) {
		Client client = clientRepository.findByUserNameOrEmail(usernameOrEmail, usernameOrEmail);
		if (client == null || client.getId() == null) {
			throw new UsernameNotFoundException("User : " + usernameOrEmail + " not found");
		}
		return CustomUserDetail.create(client);
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserById(Long id) {
		Client user = clientRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Error : User not found : " + id));
		return CustomUserDetail.create(user);
	}
}
