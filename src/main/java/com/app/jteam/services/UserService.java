package com.app.jteam.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.jteam.entities.User;
import com.app.jteam.repositories.DataRepository;

@Service
public class UserService {

    @Autowired
    private DataRepository repo;

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public void save(User user){
        //user.setPassword(getPasswordEncoder().encode(user.getPassword()));
        repo.save(user);
    }

}
