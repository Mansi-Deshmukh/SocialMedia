package com.socialmedia.service;
import java.math.BigInteger;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.socialmedia.dto.UserDTO;
import com.socialmedia.exception.GeneralException;
import com.socialmedia.exception.SignInException;
import com.socialmedia.exception.UserNotFoundException;
import com.socialmedia.model.User;
import com.socialmedia.repositories.PostRepo;
import com.socialmedia.repositories.UserRepo;

@Service
public class UserServiceimpl implements UserService {
    
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PostRepo postRepo;

    /*
     * exception : one email - one user
     * 
     */

    @Override
    public UserDTO createUser(UserDTO userDTO) throws SignInException {
       List<User> userList = userRepo.findAll();
       User user = userDToToUser(userDTO);
       for(User u : userList){
         if(u.getEmail().equals(user.getEmail())){
            throw new SignInException("User already registered with email : "+ user.getEmail());
         } 
       }
       userRepo.save(user);
        return userDTO;
    }



    @Override
    public UserDTO getUserById(Long id) throws UserNotFoundException {
       User user = userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with user Id : "+ id));
       UserDTO uDto = userToUserDTO(user);
       return uDto;
    }

    @Override
    public UserDTO updateUserById(Long id, UserDTO userDTO) throws UserNotFoundException, GeneralException {
        User user = userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with user Id : "+ id));
        List<User> userList = userRepo.findAll();
        User saveUser = userDToToUser(userDTO);
        for(User u : userList){
         if(u.getId() != user.getId() && u.getEmail().equals(saveUser.getEmail())){
            throw new GeneralException("User already registered with email : "+ user.getEmail());
         } 
       }
       if(saveUser.getBio() != null)
             user.setBio(saveUser.getBio());

       if(saveUser.getName() != null){
             user.setName(saveUser.getName());
       }


              //    user.setCreatedAt(saveUser.getCreatedAt());
              //    user.setEmail(saveUser.getEmail());
       

        userRepo.save(user);
        return userDTO;
    }

    @Override
    public String deleteUserById(Long id) throws UserNotFoundException {
        User user = userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with user Id : "+ id));
        userRepo.delete(user);
        return " User Deleted ..";
    }

    @Override
    public Integer getTotalNumberOfUser() throws GeneralException{
         List<User> userList = userRepo.findAll();
         if(userList.isEmpty()){
            throw new GeneralException("No user registered yet ...");
         }
         return userList.size();
    }
    // @Override
    // public Integer getTotalNumberOfLikesByUser(Long id) throws UserNotFoundException {
    //    User user = userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with user id :  "+ id));
    //    List<Post> post = postRepo.findAll();
    //    Integer countLikes = 0;
    //    for(Post p : post){
    //       if(p.getUser().getId() == id){
    //          countLikes++;
    //       }
    //    }
    //    return countLikes;
    // }


    @Override
    public List<User> getTopFiveUsers() {
        return userRepo.findAll(Sort.by(Sort.Direction.DESC, "postCount")).stream()
        .limit(5)
        .collect(Collectors.toList());
    }



    public User userDToToUser(UserDTO userDTO){

        User user = new User();
        user.setBio(userDTO.getBio());
        user.setCreatedAt(userDTO.getCreatedAt());
        user.setEmail(userDTO.getEmail());
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setUpdatedAt(userDTO.getUpdatedAt());

        return user;
    }

    public UserDTO userToUserDTO(User user){

        UserDTO userDTO = new UserDTO();
        userDTO.setBio(user.getBio());
        userDTO.setCreatedAt(user.getCreatedAt());
        userDTO.setEmail(user.getEmail());
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setUpdatedAt(user.getUpdatedAt());

        return userDTO;
    }









   
    
}
