package com.stepashka.hibernate.service.impl;

import com.stepashka.hibernate.entity.GoodEntity;
import com.stepashka.hibernate.entity.GoodImageEntity;
import com.stepashka.hibernate.exception.NotFoundException;
import com.stepashka.hibernate.repository.GoodImageRepository;
import com.stepashka.hibernate.repository.GoodRepository;
import com.stepashka.hibernate.service.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class GoodService implements CRUDService<GoodEntity> {

    private GoodRepository goodRepository;
    private GoodImageRepository goodImageRepository;

    @Autowired
    public GoodService(GoodRepository goodRepository, GoodImageRepository goodImageRepository){
        this.goodRepository = goodRepository;
        this.goodImageRepository = goodImageRepository;
    }

    @Override
    public List<GoodEntity> getAll() {
        return goodRepository.findAll();
    }

    public GoodEntity getById(Long id) throws NotFoundException {
        Optional<GoodEntity> optionalGood = goodRepository.findById(id);
        if(optionalGood.isEmpty()){
            throw new NotFoundException("Product not found");
        }
        return optionalGood.get();
    }

    @Override
    public GoodEntity saveUpdate(GoodEntity entity) {
       return goodRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        goodRepository.deleteById(id);
    }

    public GoodImageEntity saveUpdateGoodImage(GoodImageEntity goodImageEntity) {
        return goodImageRepository.save(goodImageEntity);
    }

    public List<GoodImageEntity> getAllImagesById(Long id) {
        return goodImageRepository.findAllByGoodId(id);
    }
}
