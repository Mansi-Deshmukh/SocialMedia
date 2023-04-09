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
import org.springframework.web.bind.annotation.RestController;

import com.socialmedia.dto.PostDTO;
import com.socialmedia.exception.PostNotFoundException;
import com.socialmedia.exception.UserNotFoundException;
import com.socialmedia.model.Post;
import com.socialmedia.service.PostService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;


@RestController
// @RequestMapping("/posts")
public class PostController {
    
   @Autowired
   private PostService postService;
    
    @PostMapping("/posts")
    public ResponseEntity<PostDTO> createPostHandler(PostDTO postDTO) throws UserNotFoundException {
        PostDTO post = postService.createPost(postDTO);
        return new ResponseEntity<PostDTO>(post, HttpStatus.CREATED);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<PostDTO> getPostByIdHandler(@PathVariable("id") Long id) throws PostNotFoundException{
         PostDTO post = postService.getPostById(id);
         return new ResponseEntity<PostDTO>(post, HttpStatus.OK);

    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<PostDTO> updatePostByIdHandler(@PathVariable("id") Long id, @RequestBody PostDTO postDTO) throws PostNotFoundException{
           PostDTO post = postService.updatedPostById(id, postDTO);
           return new ResponseEntity<PostDTO>(post, HttpStatus.OK);

     }

     @DeleteMapping("/posts/{id}")
     public ResponseEntity<String> deletePostByIdHandler(@PathVariable("id") Long id) throws PostNotFoundException{
        String message = postService.deletePostById(id);
        return new ResponseEntity<String>(message, HttpStatus.OK);

     }

     @PostMapping("/posts/{id}/like")
     public ResponseEntity<Void> likePostByIdHandler(@PathVariable("id") Long id) throws PostNotFoundException{
        postService.likePostById(id);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
     }

     @PostMapping("/posts/{id}/unlike")
     public ResponseEntity<Void> dislikePostByIdHandler(@PathVariable("id") Long id) throws PostNotFoundException{
      postService.unlikePostById(id);
      return new ResponseEntity<Void>(HttpStatus.CREATED);
     }

     @GetMapping("/analytics/post")
     public ResponseEntity<Integer> getTotalNumberOfPostHandler() throws PostNotFoundException{
        Integer count = postService.gettotalNumberOfPost();
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
     }

     @GetMapping("/analytics/post/top-liked")
     public ResponseEntity<List<Post>> getTopFivePostsHandler() throws PostNotFoundException{
        List<Post> postList = postService.topFiveLikedPost();
        return new ResponseEntity<List<Post>>(postList, HttpStatus.OK);
     }
}
