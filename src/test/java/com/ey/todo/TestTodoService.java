package com.ey.todo;

import com.ey.todo.controller.TodoController;
import com.ey.todo.domain.User;
import com.ey.todo.persistence.TodoRepository;
import com.ey.todo.persistence.UserRepository;
import com.ey.todo.service.TodoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TodoController.class)
public class TestTodoService {


    @MockBean
    private TodoService todoService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private TodoRepository todoRepository;

    @Test
    public void getTaskListByUserId() throws Exception{

        User user = new User();
        user.setId(1l);

        Mockito.when(todoService.getTaskListByUserId(1l)).thenReturn(user);

    }

}
