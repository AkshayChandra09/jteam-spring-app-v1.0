package com.app.jteam;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.app.jteam.repositories.DataRepository;
import com.app.jteam.config.CustomUserDetails;
import com.app.jteam.entities.Role;
import com.app.jteam.entities.User;
import com.app.jteam.repositories.DataRepository;
import com.app.jteam.services.UserService;

@SpringBootApplication
public class JteamSpringApp1Application {

	public static void main(String[] args) {
		SpringApplication.run(JteamSpringApp1Application.class, args);
	}
	
	@Autowired
	public void authenticationManager(AuthenticationManagerBuilder builder, DataRepository repository, UserService service) throws Exception {
		//Setup a default user if db is empty
		/*if (repository.count()==0)
			service.save(new User("user", "user", Arrays.asList(new Role("USER"), new Role("ACTUATOR"))));
		else
			service.save(new User("s_d228", "s_d228", Arrays.asList(new Role("ADMIN"))));*/
		
		builder.userDetailsService(userDetailsService(repository));
		//.passwordEncoder(passwordEncoder);
	}

	/**
	 * We return an istance of our CustomUserDetails.
	 * @param repository
	 * @return
     */
	private UserDetailsService userDetailsService(final DataRepository repository) {
		return user_name -> new CustomUserDetails(repository.findByUserName(user_name));
	}
}
