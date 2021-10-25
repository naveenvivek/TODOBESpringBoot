package com.ey.todo.domain;

import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@ApiModel(description="Details about User")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="USER_SEQ")
	@SequenceGenerator(name = "user-id-generator",  sequenceName="USER_SEQ", initialValue = 1)
	private Long id;
	
	private String username;
	private String password;
	
	@OneToMany
	private List<Todo> todoList = new ArrayList<Todo>();
	
	public User()
	{
		
	}
	
	public User(Long id, String username, String password, List<Todo> todoList) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.todoList = todoList;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Todo> getTodoList() {
		return todoList;
	}

	public void setTodoList(List<Todo> todoList) {
		this.todoList = todoList;
	}
	

}
