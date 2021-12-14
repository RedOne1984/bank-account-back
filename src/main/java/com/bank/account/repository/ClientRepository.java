package com.bank.account.repository;

import com.bank.account.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByUserNameOrEmail(String userName, String email);
}
