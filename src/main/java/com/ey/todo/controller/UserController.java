package com.ey.todo.controller;

import com.ey.todo.domain.User;
import com.ey.todo.model.AddUserRequest;
import com.ey.todo.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    @ApiOperation(value = "Add New User", notes = "Return User Created", response = User.class)
    public ResponseEntity<User> addUser(@RequestBody AddUserRequest userRequest) {

        logger.info("Inside addUser {}" , userRequest.getUsername());

        User user = userService.addUser(userRequest);

        if(user != null) return new ResponseEntity<>(user, HttpStatus.CREATED);

        return new ResponseEntity<User>(new User(), HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{userId}")
    @ApiOperation(value = "Delete User", notes = "Return User Deleted Status", response = ResponseEntity.class)
    public ResponseEntity deleteUser(@PathVariable Long userId) {

        logger.info("Inside deleteUser {}" , userId);

        Boolean success = userService.deleteUser(userId);

        if(success) return new ResponseEntity(HttpStatus.OK);
        else return new ResponseEntity(HttpStatus.BAD_REQUEST);

    }


}
