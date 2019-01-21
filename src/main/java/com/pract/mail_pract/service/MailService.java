package com.pract.mail_pract.service;

import org.hibernate.internal.HEMLogging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.text.html.HTMLDocument;

@PropertySource("classpath:application.properties")
@Service
public class MailService {

      @Autowired
      JavaMailSender javaMailSender;
         @Autowired
         Environment env;

         public int send(String email, MultipartFile file) throws MessagingException {
             int code = (int)Math.round(1000 + Math.random()*9999);
             String text = "<h3>hello,pls enter this code to registration window</h3>" + code;
             MimeMessage mimeMessage = javaMailSender.createMimeMessage();
             MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
             try {
                 mimeMessage.setFrom(new InternetAddress(env.getProperty("spring.mail.username")));
                 helper.setTo(email);
                 helper.setText(text,true);
                 helper.addAttachment(file.getOriginalFilename(),file);
                 helper.setSubject("hi");
             } catch (MessagingException e) {
                 e.printStackTrace();
             }
                 javaMailSender.send(mimeMessage);

             return code;
         }
}
