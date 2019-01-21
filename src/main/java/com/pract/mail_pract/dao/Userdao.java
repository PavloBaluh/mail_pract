package com.pract.mail_pract.dao;

import com.pract.mail_pract.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface Userdao extends JpaRepository<User,Integer>{
}
