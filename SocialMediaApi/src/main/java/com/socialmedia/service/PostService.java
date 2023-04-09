package com.socialmedia.service;

import java.util.List;

import com.socialmedia.dto.PostDTO;
import com.socialmedia.exception.PostNotFoundException;
import com.socialmedia.exception.UserNotFoundException;
import com.socialmedia.model.Post;

public interface PostService {
 
    public PostDTO createPost(PostDTO postDto) throws UserNotFoundException;
    public PostDTO getPostById(Long id) throws PostNotFoundException;
    public PostDTO updatedPostById(Long id, PostDTO postDTO) throws PostNotFoundException;
    public String deletePostById(Long id) throws PostNotFoundException;
    public void likePostById(Long id) throws PostNotFoundException;
    public void unlikePostById(Long id) throws PostNotFoundException;
    public Integer gettotalNumberOfPost() throws PostNotFoundException;
    public List<Post> topFiveLikedPost() throws PostNotFoundException;
}
