package com.ey.todo.controller;

import com.ey.todo.domain.Todo;
import com.ey.todo.domain.User;
import com.ey.todo.model.AddToDoRequest;
import com.ey.todo.service.TodoService;
import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/todo")
@CrossOrigin(origins = "http://localhost:3000")
public class TodoController {

	Logger logger = LoggerFactory.getLogger(TodoController.class);

	@Autowired
	private TodoService todoService;

	@GetMapping("/{userId}")
	@ApiOperation(value = "Get User Task List based on UserId", notes = "Return User's Task List", response = User.class)
	public ResponseEntity<User> getTaskListByUserId(@PathVariable Long userId) {

		logger.info("Inside getTaskListByUserId {}" , userId);

		User user = todoService.getTaskListByUserId(userId);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@PostMapping("/{userId}/todos")
	@ApiOperation(value = "Add Task for UserId", notes = "Return Success Status", response = User.class)
	public ResponseEntity<User> addTodo(@PathVariable Long userId, @RequestBody AddToDoRequest todoRequest) {

		User user = todoService.addTodo(userId, todoRequest);

		if(user != null) return new ResponseEntity<User>(user, HttpStatus.CREATED);

		return new ResponseEntity<User>(new User(), HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("{userId}/deleteTodo/{todoId}")
	@ApiOperation(value = "Remove Task for UserId", notes = "Return Delete Status", response = Long.class)
	public ResponseEntity<Long> removeTodo(@PathVariable Long userId, @PathVariable Long todoId) {

		boolean success =  todoService.removeTodo(userId, todoId);

		if(success)  return new ResponseEntity<>(todoId, HttpStatus.OK);
		else return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	@PutMapping("/updateTodo/{todoId}")
	@ApiOperation(value = "Update Task Completion Status for UserId", notes = "Return Updated Status", response = Todo.class)
	public  ResponseEntity toggleTodoCompleted(@PathVariable Long todoId, @RequestBody AddToDoRequest todoRequest) {

		Todo todo = todoService.toggleTodoCompleted(todoId, todoRequest);

		if(todo != null)  return new ResponseEntity<>(todo, HttpStatus.OK);
		else return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}


}
