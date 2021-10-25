package com.ey.todo.controller;

import com.ey.todo.domain.Todo;
import com.ey.todo.domain.User;
import com.ey.todo.model.AddToDoRequest;
import com.ey.todo.model.GetUserRequest;
import com.ey.todo.model.ToDoItem;
import com.ey.todo.service.TodoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/todo")
@CrossOrigin(origins = "http://localhost:3000")
public class TodoController {

	@Autowired
	private TodoService todoService;

	@GetMapping("/{userId}")
	@ApiOperation(value = "Get User Task List based on UserId", notes = "Return User's Task List", response = User.class)
	public User getTaskListByUserId(@PathVariable Long userId) {

		User user = todoService.getTaskListByUserId(userId);
		String pattern = "dd/MM/yyyy hh:mm:ss a";

		List<ToDoItem> toDoItems = user.getTodoList().stream()
				.map(todo -> ToDoItem.builder().id(todo.getId()).description(todo.getDescription()).completed(todo.getCompleted()).
						lastUpdatedTime(todo.getLastUpdatedTime().format(DateTimeFormatter.ofPattern(pattern))).build())
				.collect(Collectors.toList());

		return user;
	}

	@PostMapping("/{userId}/todos")
	@ApiOperation(value = "Add Task for UserId", notes = "Return Success Status", response = String.class)
	public User addTodo(@PathVariable Long userId, @RequestBody AddToDoRequest todoRequest) {

		return  todoService.addTodo(userId, todoRequest);
	}

	@DeleteMapping("{userId}/deleteTodo/{todoId}")
	@ApiOperation(value = "Remove Task for UserId", notes = "Return Success Status", response = String.class)
	public ResponseEntity<Long> removeTodo(@PathVariable Long userId, @PathVariable Long todoId) {

		boolean success =  todoService.removeTodo(userId, todoId);

		if(success)  return new ResponseEntity<>(todoId, HttpStatus.OK);
		else return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	@PutMapping("/updateTodo/{todoId}")
	@ApiOperation(value = "Update Task Completion Status for UserId", notes = "Return Success Status", response = String.class)
	public  ResponseEntity toggleTodoCompleted(@PathVariable Long todoId, @RequestBody AddToDoRequest todoRequest) {

		Todo todo = todoService.toggleTodoCompleted(todoId, todoRequest);

		if(todo != null)  return new ResponseEntity<>(todo, HttpStatus.OK);
		else return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}


}
