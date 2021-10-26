package com.ey.todo.service;

import com.ey.todo.domain.Todo;
import com.ey.todo.domain.User;
import com.ey.todo.model.AddToDoRequest;
import com.ey.todo.persistence.TodoRepository;
import com.ey.todo.persistence.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
public class TodoService {

    Logger logger = LoggerFactory.getLogger(TodoService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TodoRepository todoRepository;

    public User getTaskListByUserId(@PathVariable Long userId) {

        logger.info("Inside getTaskListByUserId {}" , userId);
        try {
            return userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
        }
        catch(NoSuchElementException nse)
        {
            logger.error("NoSuchElementException Inside getTaskListByUserId {} {}" , userId, nse.getMessage());
            return null;
        }
        catch(Exception e)
        {
            logger.info("Exception Inside getTaskListByUserId {} {}" , userId, e.getMessage());
            return null;
        }
    }


    public User addTodo(@PathVariable Long userId, @RequestBody AddToDoRequest todoRequest) {

        logger.info("Inside addTodo {} {}" , userId, todoRequest.getDescription());

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
            logger.error("NoSuchElementException Inside addTodo {} {} {}" , userId, todoRequest.getDescription(), nse.getMessage());
            return null;
        }
        catch(Exception e){
            logger.error("Exception Inside addTodo {} {} {}" , userId, todoRequest.getDescription(), e.getMessage());
            return null;
        }
    }

    public boolean removeTodo(@PathVariable Long userId, @PathVariable Long todoId) {

        logger.info("Inside removeTodo {} {}" , userId, todoId);

        try {
            User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
            Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new NoSuchElementException());
            user.getTodoList().remove(todo);
            todoRepository.delete(todo);
            return Boolean.TRUE;
        }
        catch(NoSuchElementException nse){
            logger.error("NoSuchElementException Inside removeTodo {} {} {}" , userId, todoId, nse.getMessage());
            return Boolean.FALSE;
        }
        catch(Exception e){
            logger.error("Exception Inside removeTodo {} {} {}" , userId, todoId, e.getMessage());
            return Boolean.FALSE;
        }
    }

    public Todo toggleTodoCompleted(@PathVariable Long todoId, @RequestBody AddToDoRequest todoRequest) {

        logger.info("Inside toggleTodoCompleted {} {}" , todoId, todoRequest.getDescription());

        try {
            Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new NoSuchElementException());
            todo.setCompleted(!todo.getCompleted());
            todo.setLastUpdatedTime(LocalDateTime.now());
            todoRepository.save(todo);
            return todo;
        }
        catch(NoSuchElementException nse){
            logger.error("NoSuchElementException Inside toggleTodoCompleted {} {}" , todoId, nse.getMessage());
            return null;
            }
        catch(Exception e){
            logger.error("Exception Inside toggleTodoCompleted {} {}" , todoId, e.getMessage());
            return null;
            }

    }

}
