//package com.stepashka.hibernate.mail;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/mail")
//public class MailController {
//
//    private MailService mailService;
//
//    public MailController(MailService mailService){
//        this.mailService = mailService;
//    }
//
//    @GetMapping
//    public void sendMessage(){
//        mailService.sendSimpleMessage("kardash-99@list.ru", "First attempt", "Hello I am working");
//    }
//
//}
