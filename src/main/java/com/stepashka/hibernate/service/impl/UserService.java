package com.stepashka.hibernate.service.impl;

import com.stepashka.hibernate.entity.UserEntity;
import com.stepashka.hibernate.exception.NotFoundException;
import com.stepashka.hibernate.exception.UserAlreadyExistsException;
import com.stepashka.hibernate.repository.UserRepository;
import com.stepashka.hibernate.service.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class UserService implements CRUDService<UserEntity> {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity getById(Long id) throws NotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Override
    public UserEntity saveUpdate(UserEntity entity) {
        return userRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public void deleteByPhoneNumber(String phoneNumber){
        userRepository.deleteByPhoneNumber(phoneNumber);
    }

    public UserEntity create(UserEntity user) throws UserAlreadyExistsException {
        if (userRepository.existsByPhoneNumber(user.getUsername())) {
            throw new UserAlreadyExistsException("User with this phone number already exists");
        }
        return userRepository.save(user);
    }

    public UserEntity getByPhoneNumber(String phoneNumber) throws UsernameNotFoundException{
        return userRepository.findByPhoneNumber(phoneNumber).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public UserDetailsService userDetailsService() {
        return this::getByPhoneNumber;
    }

    public UserEntity getCurrentUser() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByPhoneNumber(username);
    }
}
