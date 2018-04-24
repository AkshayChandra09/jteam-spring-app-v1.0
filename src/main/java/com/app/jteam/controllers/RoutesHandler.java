package com.app.jteam.controllers;

import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.app.jteam.entities.ProjectTasks;
import com.app.jteam.entities.ProjectTeamMembers;
import com.app.jteam.entities.Task;
import com.app.jteam.entities.User;
import com.app.jteam.repositories.DataRepository;
import com.app.jteam.repositories.ProjectRepo;
import com.app.jteam.repositories.ProjectTasksRepo;
import com.app.jteam.repositories.ProjectTeamRepo;
import com.app.jteam.repositories.TasksRepo;
import com.app.jteam.repositories.TeamMemberRepo;

import com.app.jteam.services.UserService;
import com.app.jteam.util.CustomErrorType;


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
	
	@Autowired
	private ProjectTasksRepo pro_tasks_repo;
	
	@Autowired
	private UserService userService;
	
	public static final Logger logger = LoggerFactory.getLogger(RoutesHandler.class);

	
	
	
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
	
	/*@PostMapping("/login")
	@ResponseStatus()
	public User tryLogin(@RequestBody User user){
		
		User user_db = userRepository.findByUserName(user.getUser_name());
		//System.out.println(user_db);
		
		if(user.getUser_name().equals(user_db.getUser_name()) && user.getPassword().equals(user_db.getPassword()))
		{
			System.out.println("\n\nCan Login\n");
			return user_db;
		}
		
		return user;

	}*/
	
	

	
	/*********************************************************************************/
	@RequestMapping("/getUsers")
    @ResponseBody
	public List<User> findall(){
		return userRepository.findAll();
	}
	
	
	@PostMapping("/register")
	@ResponseBody
	public User formPost(@RequestBody User user) {
		//System.out.println("User Info: "+user);
	     return userRepository.save(user);
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
	 

	  
	 //for angular front end	
	 @GetMapping("/showTasks")
	 public List<Task> getUsers(){
			return task_operation.findAll();
	 }
	 
	 @GetMapping("/showTask/{id}")
	 public Task getSingleTask(@PathVariable long id){
		 return task_operation.findOne(id);
	 }
	 
	

	/***************************** Final Modules *******************************************/
	
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
	 
	 @DeleteMapping("/task/{id}")
	 public boolean deleteUser(@PathVariable Long id){
	 	task_operation.delete(id);
	 	pro_tasks_repo.delete(id);
	 	team_member.delete(id);
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
		 
		 //insert record into project_task table
		 ProjectTasks ptRecord = new ProjectTasks(task_object.getProject_id(), added_task.getId());
		 pro_tasks_repo.save(ptRecord);
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
	
	 @RequestMapping(value="/newProject", method = RequestMethod.POST)
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
	@ResponseBody
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
	
	
	@GetMapping("/tasksInProject")
	@ResponseBody
	public List<ProjectTasks> tasksInProject(){
		return pro_tasks_repo.findAll();
	}
	
	@GetMapping("/showTasksInProject/{pid}")
	@ResponseBody
	public List<Task> showTasksInProject(@PathVariable int pid){
		ArrayList<Task> tasksInProj = new ArrayList<>();
		List<ProjectTasks> projTasks =  pro_tasks_repo.findProjectTasks(pid);
		for(int i=0;i<projTasks.size();i++){
			Task t1 = task_operation.findOne((projTasks.get(i).getTask_id()));
			//database will return null if no task is found. 
			//we are not interested in sending array of null values
			if(t1!=null){
				tasksInProj.add(t1);
			}
		}
		return tasksInProj;
	}
	
	/****************************** End Project Module ***************************************/
	
	/******************************* Statistics Module ***************************************/
	@GetMapping("/dashboard_statistics")
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
	
	@GetMapping("/project_statistics/{pid}")
	@ResponseBody
	public ProjectStatistics project_statistics(@PathVariable int pid){
		ProjectStatistics stats = new ProjectStatistics();
		
		int total_tasks = pro_tasks_repo.NumberOfTasks(pid);
		stats.setTotal_tasks(total_tasks);
		stats.setNumberOfMembers(projectTeam_repo.NumberOfMembers(pid));
		stats.setBudget(project_repository.findBudget(pid));
		stats.setAdmin_name(project_repository.findAdmin(pid));
		
		//change this logic after demo
		int task_completed = 0, task_in_progress=0, task_pending=0;
		
		List<ProjectTasks> ts = pro_tasks_repo.findProjectTasks(pid);
		for(int i=0;i<ts.size();i++){
			//System.out.println((ts.get(i)).getTask_id());
			Task t1 = task_operation.findOne((ts.get(i)).getTask_id());
			if(t1.getTask_status().equalsIgnoreCase("Completed"))
				task_completed++;
			
			else if(t1.getTask_status().equalsIgnoreCase("In-Progress"))
				task_in_progress++;
			
			else task_pending++;
		}
		
		stats.setCompleted_tasks(task_completed);
		stats.setInprogress_tasks(task_in_progress);
		stats.setPending_tasks(task_pending);
		
		double percent_complete = ((double)task_completed/total_tasks) * 100 ;
		
		stats.setPercent_complete(percent_complete);
		//System.out.println("\n\nStats: ");
		
		return stats;
	 }
	
	/******************************* End Statistics Module ***************************************/
	
	/******************************* End TeamMembers Module ***************************************/
	
	@GetMapping("/showMembersProjectsList/{uid}")
	@ResponseBody
	public List<MemberProject> getMembersProjects(@PathVariable int uid){
		List<ProjectTeamMembers> ptm = projectTeam_repo.findProjects((long)uid);
		ArrayList<MemberProject> memProjectList = new ArrayList<>(); 
		//System.out.println("id: "+ptm.get(0).getPid()+" uid: "+ptm.get(0).getUid());
		for(int i=0;i<ptm.size();i++){
			Project proj = project_repository.findOne(ptm.get(i).getPid());			
			MemberProject mp_new = new MemberProject();
			mp_new.setProject_id(proj.getId());
			mp_new.setProject_name(proj.getProject_name());
			mp_new.setAdmin_name(proj.getProject_admin());
			mp_new.setProject_desc(proj.getProject_desc());
			memProjectList.add(mp_new);	
		}
		
		return memProjectList;
	}
	
	@GetMapping("/showMemberTasks/{pid}/{uid}")
	@ResponseBody
	public List<Task> showMemberTasks(@PathVariable int pid, @PathVariable int uid){
		ArrayList<Task> tasksInProj = new ArrayList<>();
		
		List<Long> projTasks =  pro_tasks_repo.findProjectTasksIds(pid);
		List<Long> assignedTasks = team_member.findTaskIdsByUserIds((long)uid);
				
		List<Long> common = new ArrayList<Long>(projTasks);
		common.retainAll(assignedTasks);
		
		ArrayList<Task> tasks = new ArrayList<>();  
		
		for(int i=0;i<common.size();i++){
			Task t1 = task_operation.findOne(common.get(i));
			tasks.add(t1);
			//System.out.println(common.get(i));
		}
		
		return tasks;
	}
	
	/******************************* End TeamMembers Module ***************************************/
	/************************ Login *********************************************/

	/*@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody User newUser) {
		if (userService.find(newUser.getUser_name()) != null) {
			logger.error("username Already exist " + newUser.getUser_name());
			return new ResponseEntity(
					new CustomErrorType("user with username " + newUser.getUser_name() + "already exist "),
					HttpStatus.CONFLICT);
		}
		newUser.setRole("USER");
		
		return new ResponseEntity<User>(userService.save(newUser), HttpStatus.CREATED);
	}*/

	@RequestMapping("/login")
	public Principal user(Principal principal) {
		logger.info("user logged "+principal);
		return principal;
	}
	
	/*@PostMapping("
")
	public String logout() {
		return "LoggedOut!!";
	}*/
	
	@RequestMapping("/logout")
	public Boolean user() {
		return true;
	}

}

class TaskObject{
	
	public Task task_details;
	public User[] members;
	public int project_id;
	
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
	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
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

class ProjectStatistics{
	
	private int pending_tasks;
	private int inprogress_tasks;
	private int completed_tasks;
	private int total_tasks;
	private double percent_complete;
	private long numberOfMembers;
	private long budget;
	private String admin_name;
	public int getPending_tasks() {
		return pending_tasks;
	}
	public void setPending_tasks(int pending_tasks) {
		this.pending_tasks = pending_tasks;
	}
	public int getInprogress_tasks() {
		return inprogress_tasks;
	}
	public void setInprogress_tasks(int inprogress_tasks) {
		this.inprogress_tasks = inprogress_tasks;
	}
	public int getCompleted_tasks() {
		return completed_tasks;
	}
	public void setCompleted_tasks(int completed_tasks) {
		this.completed_tasks = completed_tasks;
	}
	public int getTotal_tasks() {
		return total_tasks;
	}
	public void setTotal_tasks(int total_tasks) {
		this.total_tasks = total_tasks;
	}
	public double getPercent_complete() {
		return percent_complete;
	}
	public void setPercent_complete(double percent_complete) {
		this.percent_complete = percent_complete;
	}
	public long getNumberOfMembers() {
		return numberOfMembers;
	}
	public void setNumberOfMembers(long numberOfMembers) {
		this.numberOfMembers = numberOfMembers;
	}
	public long getBudget() {
		return budget;
	}
	public void setBudget(long budget) {
		this.budget = budget;
	}
	public String getAdmin_name() {
		return admin_name;
	}
	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}
}

class MemberProject{

	private int project_id;
	private String project_name;
	private String admin_name;
	private String project_desc;
	
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
	public String getAdmin_name() {
		return admin_name;
	}
	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}
	public String getProject_desc() {
		return project_desc;
	}
	public void setProject_desc(String project_desc) {
		this.project_desc = project_desc;
	}
	public MemberProject() {
	}
}