package com.ey.todo;

import com.ey.todo.controller.UserController;
import com.ey.todo.domain.User;
import com.ey.todo.model.AddUserRequest;
import com.ey.todo.persistence.TodoRepository;
import com.ey.todo.persistence.UserRepository;
import com.ey.todo.service.UserService;
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

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class TestTodoUserController {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private TodoRepository todoRepository;

    @Test
    public void addUser() throws Exception{

        AddUserRequest addUserRequest = new AddUserRequest();
        addUserRequest.setUsername("TEST_USER_1");

        User user = new User();
        user.setId(1l);

        Mockito.when(userService.addUser(Mockito.any())).thenReturn(user);

        mvc.perform( MockMvcRequestBuilders
                        .post("/users/addUser")
                        .content(asJsonString(addUserRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void addUserNotFound() throws Exception{

        AddUserRequest addUserRequest = new AddUserRequest();
        addUserRequest.setUsername("TEST_USER_1");

        User user = new User();
        user.setId(1l);

        Mockito.when(userService.addUser(addUserRequest)).thenReturn(user);

        mvc.perform( MockMvcRequestBuilders
                        .post("/users/addUser")
                        .content(asJsonString(addUserRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }


    @Test
    public void deleteUser() throws Exception{

        Mockito.when(userService.deleteUser(Mockito.any())).thenReturn(true);

        mvc.perform( MockMvcRequestBuilders
                        .delete("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void deleteUserNotFound() throws Exception{

        Mockito.when(userService.deleteUser(Mockito.any())).thenReturn(false);

        mvc.perform( MockMvcRequestBuilders
                        .delete("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
