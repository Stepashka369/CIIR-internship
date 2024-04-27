package com.stepashka.hibernate.controller;

import com.stepashka.hibernate.dto.GoodDTO;
import com.stepashka.hibernate.dto.StorehouseDTO;
import com.stepashka.hibernate.entity.GoodEntity;
import com.stepashka.hibernate.entity.StorehouseEntity;
import com.stepashka.hibernate.exception.NotFoundException;
import com.stepashka.hibernate.mapper.GoodMapper;
import com.stepashka.hibernate.mapper.StorehouseMapper;
import com.stepashka.hibernate.service.impl.StorehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<Void> deleteStorehouse(@PathVariable Long id){
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

    @DeleteMapping("/{id}/products/{goodId}")
    public ResponseEntity<Void> deleteGoodFromStorehouse(@PathVariable Long id, @PathVariable Integer goodId) throws NotFoundException{
        StorehouseEntity storehouseEntity = storehouseService.getById(id);
        Optional<GoodEntity> goodEntityOptional = storehouseEntity.getGoods()
                .stream().filter(item -> item.getId().equals(goodId)).findFirst();
        if(goodEntityOptional.isPresent()){
            storehouseEntity.getGoods().remove(goodEntityOptional.get());
            storehouseService.saveUpdate(storehouseEntity);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
