package com.ey.todo;

import com.ey.todo.controller.TodoController;
import com.ey.todo.domain.Todo;
import com.ey.todo.domain.User;
import com.ey.todo.model.AddToDoRequest;
import com.ey.todo.persistence.TodoRepository;
import com.ey.todo.persistence.UserRepository;
import com.ey.todo.service.TodoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.NoSuchElementException;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TodoController.class)
public class TestTodoRestController {

    @Autowired
    private MockMvc mvc;

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

        mvc.perform( MockMvcRequestBuilders
                        .get("/todo/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk()).
                andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }

    @Test(expected = Exception.class)
    public void getTaskListByUserIdException() throws Exception{

        User user = new User();
        user.setId(1l);

        Mockito.when(todoService.getTaskListByUserId(1l)).thenThrow(new NoSuchElementException());


        mvc.perform( MockMvcRequestBuilders
                        .get("/todo/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void addTodo() throws Exception{

        User user = new User();
        user.setId(1l);

        AddToDoRequest addToDoRequest = new AddToDoRequest();
        addToDoRequest.setDescription("Todo List Schedule Meeting");

        Mockito.when(todoService.addTodo(Mockito.anyLong(), Mockito.any())).thenReturn(user);

        mvc.perform( MockMvcRequestBuilders
                        .post("/todo/1/todos")
                        .content(asJsonString(addToDoRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void addTodoException() throws Exception{



        AddToDoRequest addToDoRequest = new AddToDoRequest();
        addToDoRequest.setDescription("Todo List Schedule Meeting");

        Mockito.when(todoService.addTodo(Mockito.any(), Mockito.any())).thenReturn(null);


        mvc.perform( MockMvcRequestBuilders
                        .post("/todo/2/todos")
                        .content(asJsonString(addToDoRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void removeTodo() throws Exception{

        Mockito.when(todoService.removeTodo(Mockito.anyLong(), Mockito.anyLong())).thenReturn(true);

        mvc.perform( MockMvcRequestBuilders
                        .delete("/todo/1/deleteTodo/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    public void removeTodoNotFound() throws Exception{

        Mockito.when(todoService.removeTodo(Mockito.anyLong(), Mockito.anyLong())).thenReturn(false);

        mvc.perform( MockMvcRequestBuilders
                        .delete("/todo/1/deleteTodo/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }


    @Test
    public void toggleTodoCompleted() throws Exception{

        AddToDoRequest addToDoRequest = new AddToDoRequest();

        Mockito.when(todoService.toggleTodoCompleted(Mockito.anyLong(), Mockito.any())).thenReturn(new Todo());

        mvc.perform( MockMvcRequestBuilders
                        .put("/todo/updateTodo/1")
                        .content(asJsonString(addToDoRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void toggleTodoCompletedNotFound() throws Exception{

        AddToDoRequest addToDoRequest = new AddToDoRequest();

        Mockito.when(todoService.toggleTodoCompleted(Mockito.anyLong(), Mockito.any())).thenReturn(null);

        mvc.perform( MockMvcRequestBuilders
                        .put("/todo/updateTodo/1")
                        .content(asJsonString(addToDoRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
