package com.app.jteam.controllers;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.app.jteam.entities.User;
import com.app.jteam.repositories.DataRepository;



@Controller
public class RoutesHandler {
	
	@Autowired
	private DataRepository userRepository;
	

	@RequestMapping("/")
    //@ResponseBody
	public String indexPage() {
		return "index.html";
	}
	
	@RequestMapping("/register")
    @ResponseBody
	public String userRegistration() {
		return "Register";
	}
	
	@RequestMapping("/dashboard")
    @ResponseBody
	public String showDashboard() {
		return "Dashboard";
	} 
	
	
	  @PostMapping("/form")
	    public String formPost(User user, Model model) {
	        userRepository.save(user);
	        return "dashboard";
	    }
	
	@RequestMapping("/findall")
    @ResponseBody
	public List<User> findall(){
		return userRepository.findAll();
	}
	
	
}
