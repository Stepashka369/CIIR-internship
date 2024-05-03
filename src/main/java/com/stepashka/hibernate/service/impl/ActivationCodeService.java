package com.stepashka.hibernate.service.impl;

import com.stepashka.hibernate.entity.ActivationCodeEntity;
import com.stepashka.hibernate.exception.NotFoundException;
import com.stepashka.hibernate.repository.ActivationCodeRepository;
import com.stepashka.hibernate.service.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivationCodeService implements CRUDService<ActivationCodeEntity, String> {

    private ActivationCodeRepository activationCodeRepository;

    @Autowired
    public ActivationCodeService(ActivationCodeRepository activationCodeRepository){
        this.activationCodeRepository = activationCodeRepository;
    }

    @Override
    public List<ActivationCodeEntity> getAll() {
        return activationCodeRepository.findAll();
    }

    @Override
    public ActivationCodeEntity getById(String  id) throws NotFoundException {
        return activationCodeRepository.findById(id).orElseThrow(() -> new NotFoundException("Activation code not found"));
    }

    @Override
    public ActivationCodeEntity saveUpdate(ActivationCodeEntity entity) {
        return activationCodeRepository.save(entity);
    }

    @Override
    public void deleteById(String id) {
        activationCodeRepository.deleteById(id);
    }
}
