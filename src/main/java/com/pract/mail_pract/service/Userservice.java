package com.pract.mail_pract.service;

import com.pract.mail_pract.dao.Userdao;
import com.pract.mail_pract.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class Userservice {
    @Autowired
    private Userdao userdao;

    public void save(User user){
        userdao.save(user);
    }

    public void transferto(MultipartFile file) throws IOException {
        String folder = System.getProperty("user.home") + File.separator + "images" + File.separator;
        file.transferTo(new File(folder + file.getOriginalFilename()));
    }
}
