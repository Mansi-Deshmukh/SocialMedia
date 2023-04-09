package com.socialmedia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.socialmedia.dto.UserDTO;
import com.socialmedia.exception.GeneralException;
import com.socialmedia.exception.SignInException;
import com.socialmedia.exception.UserNotFoundException;
import com.socialmedia.model.User;
import com.socialmedia.service.UserService;

import io.swagger.models.Response;

@RestController
// @RequestMapping("/users")
public class UserController {
    

   @Autowired
   private UserService userService;

    @PostMapping("/users/")
    public ResponseEntity<UserDTO> createUserHandler(UserDTO userDTO) throws SignInException {
       UserDTO user = userService.createUser(userDTO);
       return new ResponseEntity<UserDTO>(user, HttpStatus.CREATED);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUserByIdHandler(@PathVariable("id") Long id) throws UserNotFoundException{
      UserDTO user = userService.getUserById(id);
      return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserDTO> updateUserByIdHandler(@PathVariable("id") Long id, @RequestBody UserDTO userDTO) throws UserNotFoundException, GeneralException{
      UserDTO user = userService.updateUserById(id, userDTO);
      return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
     }

     @DeleteMapping("/users/{id}")
     public ResponseEntity<String> deleteUserByIdHandler(@PathVariable("id") Long id) throws UserNotFoundException{
       String message = userService.deleteUserById(id);
       return new ResponseEntity<String>(message, HttpStatus.OK);
     }

     @GetMapping("/analytics/users")
     public ResponseEntity<Integer> getTotalNumberOfUserHandler() throws GeneralException{
        Integer count = userService.getTotalNumberOfUser();
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
     }

     @GetMapping("/analytics/users/top-active")
     public ResponseEntity<List<User>> getTopActiveUsersHandler(){
       List<User> list = userService.getTopFiveUsers();
       return new ResponseEntity<List<User>>(list, HttpStatus.OK);
     }
}
