package com.socialmedia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.socialmedia.model.Post;


@Repository
public interface PostRepo extends JpaRepository<Post , Long>{
    // List<Post> findByUserId(Long userId);
}
