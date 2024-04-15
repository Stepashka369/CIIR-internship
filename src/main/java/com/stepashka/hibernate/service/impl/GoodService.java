package com.stepashka.hibernate.service.impl;

import com.stepashka.hibernate.dto.GoodDTO;
import com.stepashka.hibernate.entity.GoodEntity;
import com.stepashka.hibernate.exception.NotFoundException;
import com.stepashka.hibernate.mapper.GoodMapper;
import com.stepashka.hibernate.repository.GoodRepository;
import com.stepashka.hibernate.service.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;

@Service
public class GoodService implements CRUDService {

    private GoodRepository goodRepository;

    @Autowired
    public GoodService(GoodRepository goodRepository){
        this.goodRepository = goodRepository;
    }

    public List<GoodEntity> getAllGoods(){
        return goodRepository.findAll();
    }

    public GoodEntity getById(Long id) throws NotFoundException {
        Optional<GoodEntity> optionalGood = goodRepository.findById(id);
        if(optionalGood.isEmpty()){
            throw new NotFoundException("Product not found");
        }
        return optionalGood.get();
    }

    public GoodEntity saveUpdateGood(GoodEntity entity){
       return goodRepository.save(entity);
    }
}
