package com.stepashka.hibernate.controller;

import com.stepashka.hibernate.dto.GoodDTO;
import com.stepashka.hibernate.entity.GoodEntity;
import com.stepashka.hibernate.exception.NotFoundException;
import com.stepashka.hibernate.mapper.GoodMapper;
import com.stepashka.hibernate.repository.GoodRepository;
import com.stepashka.hibernate.service.impl.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class GoodController {

    private GoodService goodService;
    private GoodMapper goodMapper;

    @Autowired
    public GoodController(GoodService goodService, GoodMapper goodMapper){
        this.goodService = goodService;
        this.goodMapper = goodMapper;
    }

    @GetMapping
    public ResponseEntity<List<GoodDTO>> getAllGoods(){
        List<GoodDTO> goodDTOList = goodService.getAllGoods()
                .stream().map(item -> goodMapper.toDTO(item)).toList();
        return ResponseEntity.ok(goodDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GoodDTO> getById(@PathVariable Long id) throws NotFoundException {
        GoodDTO goodDTO = goodMapper.toDTO(goodService.getById(id));
        return ResponseEntity.ok(goodDTO);
    }

    @PostMapping
    public ResponseEntity<GoodDTO> createUpdateGood(@RequestBody GoodDTO request){
        GoodEntity goodEntity = goodService.saveUpdateGood(goodMapper.toEntity(request));
        GoodDTO goodDTO = goodMapper.toDTO(goodEntity);
        return ResponseEntity.ok(goodDTO); //заменить правльно
    }

    @DeleteMapping("/{id}")
    public void deleteGood(@PathVariable Long id){

    }

}
