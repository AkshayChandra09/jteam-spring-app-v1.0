package com.app.jteam.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.jteam.repositories.DataRepository;
import com.app.jteam.entities.User;



@Service
public class UserService {

	@Autowired
	DataRepository userRepository;

	public User save(User user) {
		return userRepository.saveAndFlush(user);
	}

	public User update(User user) {
		return userRepository.save(user);
	}

	/*public User find(String userName) {
		return userRepository.findOneByUser_name(userName);
	}*/ 
	
	public User find(String userName) {
		return userRepository.findByUserName(userName);
	}
	
	public User find(Long id) {
		return userRepository.findOne(id);
	}
}
