package com.stepashka.hibernate.controller;

import com.stepashka.hibernate.dto.GoodDTO;
import com.stepashka.hibernate.dto.ManufacturerDTO;
import com.stepashka.hibernate.dto.StorehouseDTO;
import com.stepashka.hibernate.entity.GoodEntity;
import com.stepashka.hibernate.entity.ManufacturerEntity;
import com.stepashka.hibernate.entity.StorehouseEntity;
import com.stepashka.hibernate.exception.NotFoundException;
import com.stepashka.hibernate.mapper.GoodMapper;
import com.stepashka.hibernate.mapper.StorehouseMapper;
import com.stepashka.hibernate.service.impl.StorehouseService;
import org.apache.catalina.session.StoreBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/storehouses")
public class StorehouseController {

    private StorehouseService storehouseService;
    private StorehouseMapper storehouseMapper;
    private GoodMapper goodMapper;

    @Autowired
    public StorehouseController(StorehouseService storehouseService, StorehouseMapper storehouseMapper, GoodMapper goodMapper) {
        this.storehouseService = storehouseService;
        this.storehouseMapper = storehouseMapper;
        this.goodMapper = goodMapper;
    }

    @GetMapping
    public ResponseEntity<List<StorehouseDTO>> getAllStorehouses() {
        List<StorehouseDTO> storehouseDTOList = storehouseService.getAll()
                .stream().map(item -> storehouseMapper.toDTO(item)).toList();
        return new ResponseEntity<>(storehouseDTOList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StorehouseDTO> getById(@PathVariable Long id) throws NotFoundException {
        StorehouseDTO storehouseDTO = storehouseMapper.toDTO(storehouseService.getById(id));
        return new ResponseEntity<>(storehouseDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StorehouseDTO> createUpdateStorehouse(@RequestBody StorehouseDTO request){
        StorehouseEntity storehouseEntity = storehouseService.saveUpdate(storehouseMapper.toEntity(request));
        StorehouseDTO storehouseDTO = storehouseMapper.toDTO(storehouseEntity);
        return new ResponseEntity<>(storehouseDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteManufacturer(@PathVariable Long id){
        storehouseService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<List<GoodDTO>> getGoodsInStorehouse(@PathVariable Long id) throws NotFoundException {
        List<GoodDTO> goodDTOList = storehouseService.getById(id).getGoods()
                .stream().map(item -> goodMapper.toDTO(item)).toList();
        return new ResponseEntity<>(goodDTOList, HttpStatus.OK);
    }

    @PostMapping("/{id}/products/{goodId}")
    public ResponseEntity<Void> addGoodInStorehouse(@PathVariable Long id, @PathVariable Integer goodId) throws NotFoundException {
        StorehouseEntity storehouseEntity = storehouseService.getById(id);
        GoodEntity goodEntity = new GoodEntity();
        goodEntity.setId(goodId);
        storehouseEntity.addGood(goodEntity);
        storehouseService.saveUpdate(storehouseEntity);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
