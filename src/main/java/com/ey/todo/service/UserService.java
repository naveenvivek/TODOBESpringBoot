package com.ey.todo.service;

import com.ey.todo.domain.User;
import com.ey.todo.model.AddUserRequest;
import com.ey.todo.persistence.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;


    public User addUser(AddUserRequest userRequest) {

        logger.info("Inside addUser {}" , userRequest.getUsername());

        try {
            User user = new User();
            user.setUsername(userRequest.getUsername());
            user.setPassword(userRequest.getPassword());

            return userRepository.save(user);
        }
        catch(Exception e)
        {
            logger.error("Exception Inside addUser {} {}" , userRequest.getUsername(), e.getMessage());
            return null;
        }
    }


    public boolean deleteUser(Long userId) {

        logger.info("Inside deleteUser {}" , userId);

        try {
            User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
            userRepository.delete(user);
            return Boolean.TRUE;

        }
        catch(Exception e){

            logger.error("Exception Inside deleteUser {}" , userId, e.getMessage());
             return Boolean.FALSE;
        }
    }

}
