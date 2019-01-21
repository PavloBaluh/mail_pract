package com.pract.mail_pract.controllers;

import com.pract.mail_pract.model.User;
import com.pract.mail_pract.service.MailService;
import com.pract.mail_pract.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;

@Controller
public class MainController {
    @GetMapping("/")
    public String home (){
        return "home";
    }

    @Autowired
    private Userservice userservice;
    @Autowired
    MailService mailService;


    @PostMapping("/upload")
    public @ResponseBody  int uploadData (@RequestParam("name") String name,
                                          @RequestParam("email") String email,
                                          @RequestParam("img")MultipartFile img
                                          ) throws IOException, MessagingException {
        User user = new User(name,email);
        user.setImage(img.getOriginalFilename());
        userservice.transferto(img);
        userservice.save(user);
        int code = mailService.send(email, img);
        return code;
    }


}
