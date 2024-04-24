package com.stepashka.hibernate.controller;

import com.stepashka.hibernate.dto.GoodDTO;
import com.stepashka.hibernate.dto.GoodImageDTO;
import com.stepashka.hibernate.entity.GoodEntity;
import com.stepashka.hibernate.entity.GoodImageEntity;
import com.stepashka.hibernate.exception.NotFoundException;
import com.stepashka.hibernate.mapper.GoodImageMapper;
import com.stepashka.hibernate.mapper.GoodMapper;
import com.stepashka.hibernate.service.impl.GoodService;
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

@RestController
@RequestMapping("/products")
public class GoodController {

    private GoodService goodService;
    private GoodMapper goodMapper;
    private GoodImageMapper goodImageMapper;

    @Autowired
    public GoodController(GoodService goodService, GoodMapper goodMapper, GoodImageMapper goodImageMapper){
        this.goodService = goodService;
        this.goodMapper = goodMapper;
        this.goodImageMapper = goodImageMapper;
    }

    @GetMapping
    public ResponseEntity<List<GoodDTO>> getAllGoods(){
        List<GoodDTO> goodDTOList = goodService.getAll()
                .stream().map(item -> goodMapper.toDTO(item)).toList();
        return new ResponseEntity<>(goodDTOList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GoodDTO> getById(@PathVariable Long id) throws NotFoundException {
        GoodDTO goodDTO = goodMapper.toDTO(goodService.getById(id));
        return new ResponseEntity<>(goodDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GoodDTO> createUpdateGood(@RequestBody GoodDTO request){
        GoodEntity goodEntity = goodService.saveUpdate(goodMapper.toEntity(request));
        GoodDTO goodDTO = goodMapper.toDTO(goodEntity);
        return new ResponseEntity<>(goodDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGood(@PathVariable Long id){
        goodService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/image")
    public ResponseEntity<GoodImageDTO> createUpdateGoodImage(@RequestBody GoodImageDTO request){
        GoodImageEntity goodImageEntity = goodService.saveUpdateGoodImage(goodImageMapper.toEntity(request));
        GoodImageDTO goodImageDTO = goodImageMapper.toDTO(goodImageEntity);
        return new ResponseEntity<>(goodImageDTO, HttpStatus.CREATED);
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<List<GoodImageDTO>> getImageById(@PathVariable Long id) {
        List<GoodImageDTO> goodDTOList = goodService.getAllImagesById(id)
                .stream().map(item -> goodImageMapper.toDTO(item)).toList();
        return new ResponseEntity<>(goodDTOList, HttpStatus.OK);
    }
}
