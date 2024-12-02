package com.example.Bank.cotrollers;

import com.example.Bank.Models.ClientModel;
import com.example.Bank.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/client")
@CrossOrigin
public class ClientController {
    @Autowired
    ClientService clientService;

    //get
    @GetMapping()
    public ArrayList<ClientModel> findAllClients(){
        return clientService.findAllClients();
    }

    //findByAccount
    @CrossOrigin
    @GetMapping("/findByAccount={account}")
    public Optional<ClientModel> findClientByAccount(@PathVariable("account") String account){
        return clientService.findClientByAccount(account);
    }

    //findByName
    @GetMapping("/findByName={name}")
    public Optional<ClientModel> findClientByName(@PathVariable("name") String name){
        return clientService.findClientByName(name);
    }

    //delete
    @CrossOrigin
    @DeleteMapping("/deleteByAccount={account}") //localhost:8080/client/deleteByAccount=account
    public String deleteClient(@PathVariable("account") String account){
        return clientService.deleteClient(account);
    }

    //editar
    @PutMapping()
    public ClientModel updateClient(@RequestBody ClientModel client){
        return clientService.editSaveClient(client);
    }

    //post
    @PostMapping()
    public ClientModel saveClient(@RequestBody ClientModel client){
        return clientService.saveClient(client);
    }


}
