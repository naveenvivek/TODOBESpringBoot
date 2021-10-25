package com.ey.todo.service;

import com.ey.todo.domain.Todo;
import com.ey.todo.domain.User;
import com.ey.todo.model.AddToDoRequest;
import com.ey.todo.persistence.TodoRepository;
import com.ey.todo.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
public class TodoService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TodoRepository todoRepository;

    public User getTaskListByUserId(@PathVariable Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
    }


    public User addTodo(@PathVariable Long userId, @RequestBody AddToDoRequest todoRequest) {

        try {
            User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());

            Todo todo = new Todo();
            todo.setTitle("Title");
            todo.setDescription(todoRequest.getDescription());
            todo.setLastUpdatedTime(LocalDateTime.now());
            user.getTodoList().add(todo);

            todoRepository.save(todo);
            userRepository.save(user);

            return user;
        }
        catch(NoSuchElementException nse){
            return null;
        }
        catch(Exception e){
            return null;
        }
    }

    public boolean removeTodo(@PathVariable Long userId, @PathVariable Long todoId) {
        try {
            User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
            Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new NoSuchElementException());
            user.getTodoList().remove(todo);
            todoRepository.delete(todo);
            return Boolean.TRUE;
        }
        catch(NoSuchElementException nse){
            return Boolean.FALSE;
        }
        catch(Exception e){
            return Boolean.FALSE;
        }
    }

    public Todo toggleTodoCompleted(@PathVariable Long todoId, @RequestBody AddToDoRequest todoRequest) {

        try {
            Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new NoSuchElementException());
            todo.setCompleted(!todo.getCompleted());
            todoRepository.save(todo);
            return todo;
        }
        catch(NoSuchElementException nse){
            return null;
            }
        catch(Exception e){
            return null;
            }

    }

}
