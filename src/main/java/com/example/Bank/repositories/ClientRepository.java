package com.example.Bank.repositories;

import com.example.Bank.Models.ClientModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends CrudRepository<ClientModel, Long> {

    public Optional<ClientModel> findByAccount(String account);

    public Optional<ClientModel> findByName(String name);

    public Optional<ClientModel> findByEmail(String email);

    public Optional<ClientModel> findByPhone(String phone);

    public Optional<ClientModel> findByAddress(String address);

    public Optional<ClientModel> findByBalance(Double balance);
}
