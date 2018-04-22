package com.app.jteam;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.app.jteam.repositories.DataRepository;

import com.app.jteam.entities.Role;
import com.app.jteam.entities.User;
import com.app.jteam.repositories.DataRepository;
import com.app.jteam.services.UserService;

@SpringBootApplication
public class JteamSpringApp1Application {

	public static void main(String[] args) {
		SpringApplication.run(JteamSpringApp1Application.class, args);
	}
	
}
