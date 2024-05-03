package com.stepashka.hibernate.controller;

import com.stepashka.hibernate.dto.UserDTO;
import com.stepashka.hibernate.entity.UserEntity;
import com.stepashka.hibernate.mapper.UserMapper;
import com.stepashka.hibernate.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper){
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<UserDTO> userDTOList = userService.getAll()
                .stream().map(item -> userMapper.toDTO(item)).toList();
        return new ResponseEntity<>(userDTOList, HttpStatus.OK);
    }

    @GetMapping("/{email}")
    @PreAuthorize("@userService.currentUser.email.equals(#email)")
    public ResponseEntity<UserDTO> getUser(@PathVariable String email){
        UserDTO userDTO = userMapper.toDTO(userService.getByEmail(email));
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("@userService.currentUser.email.equals(#request.email)")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO request){
        UserEntity userEntity = userService.getByEmail(request.getEmail());
        userEntity.setFirstName(request.getFirstName());
        userEntity.setLastName(request.getLastName());
        userEntity.setAddress(request.getAddress());
        userEntity.setEmail(request.getEmail());
        UserDTO userDTO = userMapper.toDTO(userService.saveUpdate(userEntity));
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{email}")
    @PreAuthorize("@userService.currentUser.email.equals(#email)")
    public ResponseEntity<Void> deleteUser(@PathVariable String email){
        userService.deleteByEmail(email);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
