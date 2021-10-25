package com.ey.todo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class AddUserRequest {
	
	private String username;
	private String password;
	


}
