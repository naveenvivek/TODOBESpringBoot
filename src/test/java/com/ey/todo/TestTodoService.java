package com.ey.todo;

import com.ey.todo.domain.Todo;
import com.ey.todo.domain.User;
import com.ey.todo.model.AddToDoRequest;
import com.ey.todo.persistence.TodoRepository;
import com.ey.todo.persistence.UserRepository;
import com.ey.todo.service.TodoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestTodoService {

    @Autowired
    private TodoService todoService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private TodoRepository todoRepository;

    @Test
    public void getTaskListByUserId() throws Exception{

        User user = new User();
        user.setId(1l);

        Mockito.when(userRepository.findById(1l)).thenReturn(java.util.Optional.of(user));

        User userResp = todoService.getTaskListByUserId(1l);

        assertThat(userResp.getId()).isEqualTo(1l);

    }

    @Test
    public void getTaskListByUserIdNotFound() throws Exception{

        Mockito.when(userRepository.findById(1l)).thenThrow(new NoSuchElementException());

        User userResp = todoService.getTaskListByUserId(1l);

        assertThat(userResp).isEqualTo(null);

    }

    @Test
    public void addTodo() throws Exception{

        User user = new User();
        user.setId(1l);

        AddToDoRequest addToDoRequest= new AddToDoRequest();
        addToDoRequest.setDescription("New Task");

        Mockito.when(userRepository.findById(1l)).thenReturn(java.util.Optional.of(user));

        User userResp = todoService.addTodo(1l, addToDoRequest);

        assertThat(userResp.getId()).isEqualTo(1l);

    }

    @Test
    public void addTodoNotFoundUser() throws Exception{

        User user = new User();
        user.setId(1l);

        AddToDoRequest addToDoRequest= new AddToDoRequest();
        addToDoRequest.setDescription("New Task");

        Mockito.when(userRepository.findById(1l)).thenThrow(new NoSuchElementException());

        User userResp = todoService.addTodo(1l, addToDoRequest);

        assertThat(userResp).isEqualTo(null);

    }

    @Test
    public void removeTodo() throws Exception{

        User user = new User();
        user.setId(1l);

        Todo todo = new Todo();
        todo.setDescription("New Task");

        Mockito.when(userRepository.findById(1l)).thenReturn(java.util.Optional.of(user));
        Mockito.when(todoRepository.findById(1l)).thenReturn(java.util.Optional.of(todo));

        Boolean success = todoService.removeTodo(1l, 1l);

        assertThat(success).isEqualTo(true);

    }

    @Test
    public void removeTodoNotFound() throws Exception{

        User user = new User();
        user.setId(1l);

        Todo todo = new Todo();
        todo.setDescription("New Task");

        Mockito.when(userRepository.findById(1l)).thenReturn(java.util.Optional.of(user));
        Mockito.when(todoRepository.findById(1l)).thenThrow(new NoSuchElementException());

        Boolean success = todoService.removeTodo(1l, 1l);

        assertThat(success).isEqualTo(false);

    }

    @Test
    public void toggleTodoCompleted() throws Exception{

        User user = new User();
        user.setId(1l);

        Todo todo = new Todo();
        todo.setDescription("New Task");

        AddToDoRequest addToDoRequest= new AddToDoRequest();
        addToDoRequest.setDescription("New Task");

        Mockito.when(todoRepository.findById(1l)).thenReturn(java.util.Optional.of(todo));

        Todo todoRes = todoService.toggleTodoCompleted(1l, addToDoRequest);

        assertThat(todoRes.getDescription()).isEqualTo("New Task");

    }


    @Test
    public void toggleTodoCompletedNotFound() throws Exception{

        User user = new User();
        user.setId(1l);

        Todo todo = new Todo();
        todo.setDescription("New Task");

        AddToDoRequest addToDoRequest= new AddToDoRequest();
        addToDoRequest.setDescription("New Task");

        Mockito.when(todoRepository.findById(1l)).thenThrow(new NoSuchElementException());

        Todo todoRes = todoService.toggleTodoCompleted(1l, addToDoRequest);

        assertThat(todoRes).isEqualTo(null);

    }

}
