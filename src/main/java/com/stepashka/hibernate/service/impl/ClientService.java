package com.stepashka.hibernate.service.impl;

import com.stepashka.hibernate.entity.ClientEntity;
import com.stepashka.hibernate.repository.ClientRepository;
import com.stepashka.hibernate.service.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService implements CRUDService {
    private ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public ClientEntity getObject(Long id){
        return clientRepository.findById(id).get();
    }

}
