package com.stepashka.hibernate.security;

import com.stepashka.hibernate.dto.ActivationCodeDTO;
import com.stepashka.hibernate.entity.ActivationCodeEntity;
import com.stepashka.hibernate.entity.UserEntity;
import com.stepashka.hibernate.exception.AccountActivationException;
import com.stepashka.hibernate.exception.NotFoundException;
import com.stepashka.hibernate.exception.UserAlreadyExistsException;
import com.stepashka.hibernate.mail.MailService;
import com.stepashka.hibernate.repository.ActivationCodeRepository;
import com.stepashka.hibernate.service.impl.ActivationCodeService;
import com.stepashka.hibernate.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class AuthenticationService {
    private UserService userService;
    private ActivationCodeService activationCodeService;
    private JwtService jwtService;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private MailService mailService;

    @Autowired
    public AuthenticationService(UserService userService,ActivationCodeService activationCodeService, JwtService jwtService,
                                 PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager,
                                 MailService mailService){
        this.userService = userService;
        this.activationCodeService = activationCodeService;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.mailService = mailService;
    }

    public JwtAuthenticationResponse signUp(SignUpRequest request) throws UserAlreadyExistsException, NotFoundException, AccountActivationException {
        checkVerificationCode(request);
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(request.getFirstName());
        userEntity.setLastName(request.getLastName());
        userEntity.setEmail(request.getEmail());
        userEntity.setPassword(passwordEncoder.encode(request.getPassword()));
        userEntity.setRole(Role.ROLE_USER);
        userService.create(userEntity);
        var jwt = jwtService.generateToken(userEntity);
        return new JwtAuthenticationResponse(jwt);
    }

    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));
        var user = userService
                .userDetailsService()
                .loadUserByUsername(request.getEmail());
        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }

    public void bindVerificationCode(ActivationCodeRequest request){
        ActivationCodeEntity activationCodeEntity = new ActivationCodeEntity();
        activationCodeEntity.setId(request.getEmail());
        activationCodeEntity.setCode(generateActivationCode());
        activationCodeService.saveUpdate(activationCodeEntity);
        mailService.sendSimpleMessage(request.getEmail(), activationCodeEntity.getCode());
    }

    public void checkVerificationCode(SignUpRequest request) throws NotFoundException, AccountActivationException {
        ActivationCodeEntity code = activationCodeService.getById(request.getEmail());
        if(!code.getCode().equals(request.getCode())) {
            throw new AccountActivationException("Activation code is not correct");
        }
        activationCodeService.deleteById(request.getEmail());
    }

    private String generateActivationCode(){
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        return String.valueOf(number);
    }
}
