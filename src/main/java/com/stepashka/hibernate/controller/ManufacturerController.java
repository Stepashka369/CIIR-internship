package com.stepashka.hibernate.controller;

import com.stepashka.hibernate.dto.GoodDTO;
import com.stepashka.hibernate.dto.ManufacturerDTO;
import com.stepashka.hibernate.entity.GoodEntity;
import com.stepashka.hibernate.entity.ManufacturerEntity;
import com.stepashka.hibernate.exception.NotFoundException;
import com.stepashka.hibernate.mapper.ManufacturerMapper;
import com.stepashka.hibernate.service.impl.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manufacturers")
public class ManufacturerController {

    private ManufacturerService manufacturerService;
    private ManufacturerMapper manufacturerMapper;

    @Autowired
    public ManufacturerController(ManufacturerService manufacturerService, ManufacturerMapper manufacturerMapper) {
        this.manufacturerService = manufacturerService;
        this.manufacturerMapper = manufacturerMapper;
    }

    @GetMapping
    public ResponseEntity<List<ManufacturerDTO>> getAllManufacturers() {
        List<ManufacturerDTO> manufacturerDTOList = manufacturerService.getAll()
                .stream().map(item -> manufacturerMapper.toDTO(item)).toList();
        return new ResponseEntity<>(manufacturerDTOList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManufacturerDTO> getById(@PathVariable Long id) throws NotFoundException {
        ManufacturerDTO manufacturerDTO = manufacturerMapper.toDTO(manufacturerService.getById(id));
        return new ResponseEntity<>(manufacturerDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ManufacturerDTO> createUpdateManufacturer(@RequestBody ManufacturerDTO request){
        ManufacturerEntity manufacturerEntity = manufacturerService.saveUpdate(manufacturerMapper.toEntity(request));
        ManufacturerDTO manufacturerDTO = manufacturerMapper.toDTO(manufacturerEntity);
        return new ResponseEntity<>(manufacturerDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteManufacturer(@PathVariable Long id){
        manufacturerService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
