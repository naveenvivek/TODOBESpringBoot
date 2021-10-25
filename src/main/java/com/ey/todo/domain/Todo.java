package com.ey.todo.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@ApiModel(description="Details about Todo")
public class Todo {
	
	
	@ApiModelProperty(notes ="Task's unique id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="TODO_SEQ")
	@SequenceGenerator(name = "todo-id-generator",  sequenceName="TODO_SEQ", initialValue = 1)
	private Long id;
	
	@NotBlank
	@ApiModelProperty(notes ="Task's Title")
	private String title;	


	@NotBlank
	@ApiModelProperty(notes ="Task's Description")
	private String description;
	
	@ApiModelProperty(notes ="Task's completion status")
	private Boolean completed = Boolean.FALSE;
	
	@ApiModelProperty(notes ="Task's lastupdated Time")
	private LocalDateTime lastUpdatedTime;
	
	public Todo() {	
		
	}		

	public Todo(Long id, @NotBlank String title, @NotBlank String description, Boolean completed,
                LocalDateTime lastUpdatedTime) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.completed = completed;
		this.lastUpdatedTime = lastUpdatedTime;
	}




	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public LocalDateTime getLastUpdatedTime() {	return lastUpdatedTime;	}

	public void setLastUpdatedTime(LocalDateTime lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	
}
