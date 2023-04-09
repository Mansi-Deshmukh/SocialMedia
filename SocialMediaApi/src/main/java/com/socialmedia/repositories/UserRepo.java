package com.socialmedia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.socialmedia.model.User;

@Repository
public interface UserRepo extends JpaRepository<User , Long>{
    
    // @Query(value = "SELECT u.id, u.name, u.email, u.bio, u.created_at, u.updated_at, COUNT(p.user_id) as post_count " +
    // "FROM Users u " +
    // "LEFT JOIN Posts p ON u.id = p.user_id " +
    // "GROUP BY u.id " +
    // "ORDER BY post_count DESC " +
    // "LIMIT 5", nativeQuery = true)
    // public List<Object[]> findTop5UsersByPostCount();
    // @Query("SELECT new com.socialmedia.dto.UserDTO(u.id, u.name, u.email, u.bio, u.createdAt, u.updatedAt, COUNT(p.user)) " +
    //    "FROM User u LEFT JOIN Post p ON u.id = p.user.id " +
    //    "GROUP BY u.id " +
    //    "ORDER BY COUNT(p.user) DESC")
    // public List<User> findTop5UsersByPostCount();
}
