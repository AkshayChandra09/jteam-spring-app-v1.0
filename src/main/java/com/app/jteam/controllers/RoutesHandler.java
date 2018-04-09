package com.app.jteam.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.jteam.controllers.TeamObject;
import com.app.jteam.entities.AssignedTeamMember;
import com.app.jteam.entities.Project;
import com.app.jteam.entities.ProjectTeamMembers;
import com.app.jteam.entities.Task;
import com.app.jteam.entities.User;
import com.app.jteam.repositories.DataRepository;
import com.app.jteam.repositories.ProjectRepo;
import com.app.jteam.repositories.ProjectTeamRepo;
import com.app.jteam.repositories.TasksRepo;
import com.app.jteam.repositories.TeamMemberRepo;


@RestController 
@RequestMapping("/api")
@CrossOrigin(origins="http://localhost:4200", allowedHeaders="*")
public class RoutesHandler {
	
	@Autowired
	private DataRepository userRepository;
	@Autowired
	private TasksRepo task_operation;
	@Autowired
	private TeamMemberRepo team_member;
	@Autowired
	private ProjectRepo project_repository;
	
	
	@Autowired
	private ProjectTeamRepo projectTeam_repo;
	
	@RequestMapping("/")
    @ResponseBody
	public String indexPage() {
		return "index.html";
	}
	
	
	//lame approach
	/*@GetMapping("/login/{user_name}/{password}")
	public String tryLogin_old(@PathVariable String user_name, @PathVariable String password){
		User u = userRepository.findByUserName(user_name);
		System.out.println("\n"+u.getId()+"\t"+u.getUser_name()+"\t"+u.getPassword()+"\n\n");
		
		if(password.equals(u.getPassword())){
			return "Successful..";
		}
		else
			return "Login failed...";
	}*/
	
	@PostMapping("/login")
	@ResponseStatus()
	public User tryLogin(@RequestBody User user){
		
		User u = userRepository.findByUserName(user.getUser_name());
		if(u!=null)
			System.out.println("\n\nUser found\n");
		else
			System.out.println("\n\nUser Not Found\n");
		//userRepository.save(user);
		return user;
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
	  @ResponseBody
	  public User formPost(@RequestBody User user) {
	       return userRepository.save(user);
	   }
	
	@RequestMapping("/getUsers")
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
	 
	 @RequestMapping("/task")  
	 @ResponseBody
	 public String add_task() {
		 String name = "one to x";
		 String deadline = "10-12-2017";
		 String priority = "high";
		 String status="completed";
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
				task_operation.save(addTask);

				ArrayList<Long> user_ids = new ArrayList<Long>();
				user_ids.add((long) 8);
				user_ids.add((long) 9);
				user_ids.add((long) 10);

					
				for(int i=0;i<user_ids.size();i++){
					AssignedTeamMember tm = new AssignedTeamMember();
					tm.setParameters(addTask.getId(),user_ids.get(i));
					//User user = new User();
					//user = userRepository.findOne(user_ids.get(i));
					///user.assignTask(addTask.getId());
					team_member.save(tm);
				}
				
				return "Task Added";
		  	}
		  	catch (Exception ex) {
			    return "Error creating the task: " + ex.toString();
		  		
		  	} 
	  }
	  
	 //for angular front end	
	 @GetMapping("/showTasks")
	 public List<Task> getUsers(){
			return task_operation.findAll();
	 }
	 
	 @GetMapping("/showTask/{id}")
	 public Task getSingleTask(@PathVariable long id){
		 return task_operation.findOne(id);
	 }
	 
	//for angular front end
	 @PutMapping("/task")
	 public Task updateTask(@RequestBody Task task){
		return task_operation.save(task);
	}
	 
	//for angular front end
	 @PostMapping("/new_task")
	 public Task add_new_task(@RequestBody Task task){		 
		return task_operation.save(task);
	 }

	/***************************** Final Modules *******************************************/
	 @DeleteMapping("/task/{id}")
	 public boolean deleteUser(@PathVariable Long id){
	 	task_operation.delete(id);
		return true;
	 } 
	 
	 
	 @DeleteMapping("/deleteMember/{tid}/{uid}")
	 public boolean deleteMember(@PathVariable Long tid, @PathVariable Long uid){
		 System.out.println(((team_member.deleteMember(tid,uid)).get(0)).getMember_task_id());
		 long id = ((team_member.deleteMember(tid,uid)).get(0)).getMember_task_id();
		 team_member.delete(id);
		 return true;
	 } 

	 
	 @PostMapping("/add_task_with_members")
	 @ResponseBody
	 public TaskObject add_task_with_members(@RequestBody TaskObject task_object){
		 task_operation.save((task_object.getTask()));
		 Task added_task = task_operation.findByName((task_object.getTask()).getTask_name());
	 
		 for(int i=0;i<(task_object.getMembers()).length;i++){
			 AssignedTeamMember assigned_members = new AssignedTeamMember();
			 assigned_members.setParameters(added_task.getId(),((task_object.getMembers())[i]).getId());
			 team_member.save(assigned_members);
		 }		  
		 return task_object;
	 }
	
	 
	 @GetMapping("/showMembers/{id}")
	 @ResponseBody
	 public ArrayList<User> getMembers(@PathVariable long id){
		ArrayList<User> user_array = new ArrayList<>();	
		List<AssignedTeamMember> assigned_to = team_member.findTaskIds(id);
		for(int i=0;i<assigned_to.size();i++){
		    User user = userRepository.findOne((assigned_to.get(i)).getTeam_member());
		    user_array.add(user); 	
		}		
		return user_array;
	}
	 
	 
	/*************************** Project ******************************************/ 
	 @GetMapping("/showProjectsList")
	 //@ResponseBody
	 public List<Project> getProjects(){
		 return project_repository.findAll();
	 }
	 
	 @GetMapping("/showProject/{id}")
	//@ResponseBody
	 public Project getSingleProject(@PathVariable Integer id){
		 return project_repository.findOne(id);
	 }
	 
	 
	 @PutMapping("/editProjectDetails")
	 public Project updateProject(@RequestBody Project project){
		return project_repository.save(project);
	}
	
	 @PostMapping("/newProject")
	 public Project add_new_project(@RequestBody Project project){
		 System.out.println(project);
		return project_repository.save(project);
	 }
	
	 @DeleteMapping("/project/{id}")
	 @ResponseBody
	 public boolean deleteProjct(@PathVariable Integer id){
		 System.out.println(id);
		 project_repository.delete(id);
		return true;
	 } 
	 
	 
	 @GetMapping("/team_members/{pid}")
	 @ResponseBody
	 public List<User> getTeamMembers(@PathVariable int pid)
	 {
		 List<ProjectTeamMembers> team = projectTeam_repo.findMembers(pid);
		 ArrayList<User> user_array = new ArrayList<>();	
		 for(int i=0;i<team.size();i++){
			    User user = userRepository.findOne((long)(team.get(i)).getUid());
			    user_array.add(user); 	
		 }		
		 return user_array;
	 }
	 
	 
	 @DeleteMapping("/delete_project_member/{pid}/{uid}")
	 public boolean deleteProjectMember(@PathVariable Integer pid, @PathVariable long uid){
		 int id = ((projectTeam_repo.deleteProjectMember(pid,uid)).get(0)).getId();
		 projectTeam_repo.delete(id); 
		 return true;
	 }
	 
	 
	@PostMapping("/addProjectMembers")
	public TeamObject addProjectMembers(@RequestBody TeamObject project_members){
		int project_id = project_members.getProject_id();
		User[] members = project_members.getMembers();	
		for(int i=0;i<members.length;i++){
			ProjectTeamMembers addMembers = new ProjectTeamMembers();
			addMembers.setParameters(project_id, members[i].getId());
			projectTeam_repo.save(addMembers);
		}	
		return project_members;	
	}
	
	/****************************** End Project Module ***************************************/
	
	@GetMapping("/statistics")
	@ResponseBody
	public List<Statistics> getNo_of_teamMembers(){
		List<TeamObject> members_per_project = projectTeam_repo.stats();
		ArrayList<Statistics> statistics_for_chart = new ArrayList<>();
		for(int i=0;i<members_per_project.size();i++){
			Statistics stats = new Statistics();
			int proj_id = (members_per_project.get(i)).getProject_id();  
			stats.setProject_id(proj_id);  
			Project project = new Project();
			project = project_repository.findProject(proj_id);
			stats.setProject_name(project.getProject_name());
			stats.setNumberOfMembers((members_per_project.get(i)).getNumberOfMembers());
			statistics_for_chart.add(stats);
		}
		return statistics_for_chart;
	 }
	
}

class TaskObject{
	public Task task_details;
	public User[] members;
	
	public Task getTask() {
		return task_details;
	}
	public void setTask(Task task) {
		this.task_details = task;
	}
	public User[] getMembers() {
		return members;
	}
	public void setMembers(User[] members) {
		this.members = members;
	}
}

class Statistics{
	private int project_id;
	private String project_name;
	private long numberOfMembers;
	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public long getNumberOfMembers() {
		return numberOfMembers;
	}
	public void setNumberOfMembers(long numberOfMembers) {
		this.numberOfMembers = numberOfMembers;
	}
}