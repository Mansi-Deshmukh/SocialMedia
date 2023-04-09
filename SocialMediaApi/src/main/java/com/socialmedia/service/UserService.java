package com.socialmedia.service;

import java.math.BigInteger;

import com.socialmedia.dto.UserDTO;
import com.socialmedia.exception.GeneralException;
import com.socialmedia.exception.SignInException;
import com.socialmedia.exception.UserNotFoundException;
import com.socialmedia.model.User;

import java.util.List;

public interface UserService {
    
    public UserDTO createUser(UserDTO userDTO) throws SignInException;
    public UserDTO getUserById(Long id) throws UserNotFoundException;
    public UserDTO updateUserById(Long id, UserDTO userDTO) throws UserNotFoundException, GeneralException;
    public String deleteUserById(Long id) throws UserNotFoundException;
    public Integer getTotalNumberOfUser() throws GeneralException;
    // public Integer getTotalNumberOfLikesByUser(Long id) throws UserNotFoundException;
    public List<User> getTopFiveUsers();

}
