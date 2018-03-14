package com.app.jteam.controllers;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.jteam.entities.Task;
import com.app.jteam.entities.AssignedTeamMember;
import com.app.jteam.entities.User;
import com.app.jteam.repositories.DataRepository;
import com.app.jteam.repositories.TasksRepo;
import com.app.jteam.repositories.TeamMemberRepo;


@Controller
public class RoutesHandler {
	
	@Autowired
	private DataRepository userRepository;
	@Autowired
	private TasksRepo saveTask;
	@Autowired
	private TeamMemberRepo team_member;
	

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
    //@ResponseBody
	public String showDashboard() {
		return "card.html";
	} 
	
	
	  @PostMapping("/form")
	  @ResponseBody
	  public String formPost(User user, Model model) {
	        userRepository.save(user);
		    return "redirect:dashboard";
	    }
	
	@RequestMapping("/findall")
    @ResponseBody
	public List<User> findall(){
		return userRepository.findAll();
	}
	
	@RequestMapping("/edit")
	public String editForm(){
		return "editUserInfo.html";
	}
	
	 @RequestMapping("/update")
	  @ResponseBody
	  public String updateUser() {
		 long id = 10; String userName="amc400"; String pass="123";
	    try {
	      User user = userRepository.findOne(id);
	      user.setUser_name(userName);
	      user.setPassword(pass);
	      userRepository.save(user);
	    }
	    catch (Exception ex) {
	      return "Error updating the user: " + ex.toString();
	    }
	    return "User succesfully updated!";
	  }
	
	 @RequestMapping("/add_task")
	 public String task_form()
	 {
		 return "addTask.html";
	 }
	 
	 @PostMapping("/task")
	 @ResponseBody
	 public String add_task(String name, String deadline, String priority, String status) {
		 
		  try{
				Date assigned_date = new Date();
				Task addTask = new Task();	 
				addTask.setTask_name(name);
				addTask.setTask_assigned_date(assigned_date);
				SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");  
				Date strDate = formatter.parse(deadline);
				addTask.setTask_deadline(strDate);
				addTask.setTask_priority(priority);
				addTask.setTask_status(status);
				saveTask.save(addTask);
				
				ArrayList<Integer> user_ids = new ArrayList<Integer>();
				user_ids.add(1);
				user_ids.add(2);
				user_ids.add(3);
					
				for(int i=0;i<user_ids.size();i++){
					AssignedTeamMember tm = new AssignedTeamMember();
					tm.setParameters(1,user_ids.get(i));
					team_member.save(tm);
				}
								
				return "Task Added";
		  	}
		  	catch (Exception ex) {
			    return "Error updating the user: " + ex.toString();
			} 
	  }
	 
	
}
