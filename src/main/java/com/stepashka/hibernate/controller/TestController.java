package com.stepashka.hibernate.controller;

import com.stepashka.hibernate.dto.ClientDTO;
import com.stepashka.hibernate.dto.GoodDTO;
import com.stepashka.hibernate.dto.OrderDetailDTO;
import com.stepashka.hibernate.entity.ClientEntity;
import com.stepashka.hibernate.mapper.ClientMapper;
import com.stepashka.hibernate.mapper.GoodMapper;
import com.stepashka.hibernate.mapper.OrderDetailMapper;
import com.stepashka.hibernate.service.impl.ClientService;
import com.stepashka.hibernate.service.impl.GoodService;
import com.stepashka.hibernate.service.impl.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    private ClientService clientService;
    private GoodService goodService;
    private OrderDetailService orderDetailService;
    private GoodMapper goodMapper;
    private ClientMapper clientMapper;
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    public TestController(ClientService clientService, GoodService goodService, OrderDetailService orderDetailService,
                          GoodMapper goodMapper, ClientMapper clientMapper, OrderDetailMapper orderDetailMapper){
        this.clientService = clientService;
        this.goodService = goodService;
        this.orderDetailService =orderDetailService;
        this.goodMapper = goodMapper;
        this.clientMapper = clientMapper;
        this.orderDetailMapper = orderDetailMapper;
    }

    @GetMapping("/{id}")
    public ClientDTO getClient(@PathVariable Long id){
        //ClientEntity obj = clientService.getObject(id);
        //return goodMapper.toDTO(goodService.getObject(id));
        ClientDTO client = clientMapper.toDTO(clientService.getObject(id));
        return client;
    }

    @GetMapping("/order/{id}")
    public OrderDetailDTO getDetail(@PathVariable Long id){
        //ClientEntity obj = clientService.getObject(id);
        //return goodMapper.toDTO(goodService.getObject(id));
        OrderDetailDTO detail = orderDetailMapper.toDTO(orderDetailService.getObject(id));
        return detail;
    }
}
