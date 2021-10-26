package com.ey.todo;


import com.ey.todo.domain.User;
import com.ey.todo.model.AddUserRequest;
import com.ey.todo.persistence.TodoRepository;
import com.ey.todo.persistence.UserRepository;
import com.ey.todo.service.UserService;
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
public class TestUserService {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private TodoRepository todoRepository;

    @Test
    public void addUser() throws Exception{

        AddUserRequest addUserRequest = new AddUserRequest();
        addUserRequest.setUsername("TEST_USER");

        User user = new User();
        user.setUsername("TEST_USER");
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);

        User userResp = userService.addUser(addUserRequest);
        assertThat(userResp.getUsername()).isEqualTo("TEST_USER");

    }

    @Test
    public void addUserException() throws Exception{

        AddUserRequest addUserRequest = new AddUserRequest();
        addUserRequest.setUsername("TEST_USER");

        User user = new User();
        user.setUsername("TEST_USER");

        User userResp = userService.addUser(addUserRequest);
        assertThat(userResp).isEqualTo(null);

    }

    @Test
    public void deleteUser() throws Exception{

        AddUserRequest addUserRequest = new AddUserRequest();
        addUserRequest.setUsername("TEST_USER");

        User user = new User();
        user.setUsername("TEST_USER");
        Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.of(user));

        Boolean success = userService.deleteUser(1l);
        assertThat(success).isEqualTo(true);

    }

    @Test
    public void deleteUserNotFound() throws Exception{

        AddUserRequest addUserRequest = new AddUserRequest();
        addUserRequest.setUsername("TEST_USER");

        User user = new User();
        user.setUsername("TEST_USER");
        Mockito.when(userRepository.findById(Mockito.anyLong())).thenThrow(new NoSuchElementException());

        Boolean success = userService.deleteUser(1l);
        assertThat(success).isEqualTo(false);

    }


}
