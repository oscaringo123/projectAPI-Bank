package com.example.Bank.services;

import ch.qos.logback.core.net.server.Client;
import com.example.Bank.Models.ClientModel;
import com.example.Bank.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

    //Obtener todos los clientes registrados
    public ArrayList<ClientModel> findAllClients(){
        return (ArrayList<ClientModel>) clientRepository.findAll();
    }

    //find Client By account
    public Optional<ClientModel> findClientByAccount(String account){
        return clientRepository.findByAccount(account);
    }

    //find By Name
    public Optional<ClientModel> findClientByName(String name){
        return clientRepository.findByName(name);
    }

    //find Client By email
    public Optional<ClientModel> findClientByEmail(String email){
        return clientRepository.findByEmail(email);
    }

    //find By Phone
    public Optional<ClientModel> findClientByPhone(String phone){
        return clientRepository.findByPhone(phone);
    }

    //find Client By address
    public Optional<ClientModel> findClientByAddress(String address){
        return clientRepository.findByAddress(address);
    }

    //find By balance
    public Optional<ClientModel> findClientByBalance(Double balance){
        return clientRepository.findByBalance(balance);
    }

    //guardar un cliente
    public ClientModel saveClient(ClientModel client){

        Optional<ClientModel> accountExist = clientRepository.findByAccount(client.getAccount());
        Optional<ClientModel> nameExist = clientRepository.findByName(client.getName());
        Optional<ClientModel> emailExist = clientRepository.findByEmail(client.getEmail());
        Optional<ClientModel> phoneExist = clientRepository.findByPhone(client.getPhone());

        if (accountExist.isPresent()){
            throw new IllegalArgumentException("El numero de cuenta ya esta registrado");
        } else if (nameExist.isPresent()){
            throw new IllegalArgumentException("el nombre ya esta registrado");
        }else if(emailExist.isPresent()){
            throw new IllegalArgumentException("el email ya esta registrado");
        }else if(phoneExist.isPresent()){
            throw new IllegalArgumentException("El Numero de telefono ya esta registrado");
        }

        return clientRepository.save(client);

    }


    //save que se usara para editar un cliente
    public ClientModel editSaveClient(ClientModel client){
        // Buscar cliente que se está editando
        Optional<ClientModel> existingClient = clientRepository.findByAccount(client.getAccount());

        // Verificamos si el cliente ya existe y no es el mismo cliente que estamos editando
        if (existingClient.isPresent() && !existingClient.get().getId().equals(client.getId())) {
            throw new IllegalArgumentException("El número de cuenta ya está registrado por otro cliente.");
        }

        // Verificamos si el nombre ya existe y no es el mismo cliente
        Optional<ClientModel> nameExist = clientRepository.findByName(client.getName());
        if (nameExist.isPresent() && !nameExist.get().getId().equals(client.getId())) {
            throw new IllegalArgumentException("El nombre ya está registrado por otro cliente.");
        }

        // Verificamos si el email ya existe y no es el mismo cliente
        Optional<ClientModel> emailExist = clientRepository.findByEmail(client.getEmail());
        if (emailExist.isPresent() && !emailExist.get().getId().equals(client.getId())) {
            throw new IllegalArgumentException("El email ya está registrado por otro cliente.");
        }

        // Verificamos si el teléfono ya existe y no es el mismo cliente
        Optional<ClientModel> phoneExist = clientRepository.findByPhone(client.getPhone());
        if (phoneExist.isPresent() && !phoneExist.get().getId().equals(client.getId())) {
            throw new IllegalArgumentException("El número de teléfono ya está registrado por otro cliente.");
        }

        // guardar el cliete editado si todo esta bien
        return clientRepository.save(client);
    }


    //Delete
    public String deleteClient(String account){
        String response = "";
        Optional<ClientModel> clientFound = clientRepository.findByAccount(account);
        if(clientFound.isPresent()){
            clientRepository.delete(clientFound.get());
            response = "Client deleted successfully";
        }else{
            response = "Client not found";
        }
        return response;
    }

}
