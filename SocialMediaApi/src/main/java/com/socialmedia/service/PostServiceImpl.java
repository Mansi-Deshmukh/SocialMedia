package com.socialmedia.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.socialmedia.dto.PostDTO;
import com.socialmedia.exception.PostNotFoundException;
import com.socialmedia.exception.UserNotFoundException;
import com.socialmedia.model.Post;
import com.socialmedia.model.User;
import com.socialmedia.repositories.PostRepo;
import com.socialmedia.repositories.UserRepo;

@Service
public class PostServiceImpl implements PostService{
    
    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public PostDTO createPost(PostDTO postDto) throws UserNotFoundException {
        Post post = postDtoToPost(postDto);
        if(postDto.getUser() != null){
        
        post.setLikes(0);
        postRepo.save(post);

        Optional<User> u = userRepo.findById(post.getUser().getId());
        User user = u.get();
        if(user.getPostCount() == null){
            user.setPostCount(1l);
        }else{
             user.setPostCount(user.getPostCount() + 1);
        }
        userRepo.save(user);
        return postDto; 
       }
       else        throw new UserNotFoundException("User id not found.. Please enter user Id ..");

    }

    @Override
    public PostDTO getPostById(Long id) throws PostNotFoundException {
       Post post = postRepo.findById(id).orElseThrow(() -> new PostNotFoundException("Post not found with post Id : "+ id));
       return postToPostDTO(post);
    }

    @Override
    public PostDTO updatedPostById(Long id, PostDTO postDTO) throws PostNotFoundException {
        Post post = postRepo.findById(id).orElseThrow(() -> new PostNotFoundException("Post not found with post Id : "+ id));
        Post getPost = postDtoToPost(postDTO);
        post.setContent(getPost.getContent());
      
        post.setUpdatedAt(getPost.getUpdatedAt());

        postRepo.save(post);
        return postDTO;
    }

    @Override
    public String deletePostById(Long id) throws PostNotFoundException {
         Post post = postRepo.findById(id).orElseThrow(() -> new PostNotFoundException("Post not found with post Id : "+ id));
         postRepo.delete(post);
        return "Post Deleted ..";
    }

    @Override
    public void likePostById(Long id) throws PostNotFoundException {
        Post post = postRepo.findById(id).orElseThrow(() -> new PostNotFoundException("Post not found with post Id : "+ id));
        post.setLikes(post.getLikes()+1);

        postRepo.save(post);
    }

    @Override
    public void unlikePostById(Long id) throws PostNotFoundException {
        Post post = postRepo.findById(id).orElseThrow(() -> new PostNotFoundException("Post not found with post Id : "+ id));
        if(post.getLikes() >0){
              post.setLikes(post.getLikes()-1);
        }
        postRepo.save(post);
    }

    @Override
    public Integer gettotalNumberOfPost() throws PostNotFoundException {
        List<Post> list = postRepo.findAll();
        return list.size();
    }

    @Override
    public List<Post> topFiveLikedPost() throws PostNotFoundException {
        
        return postRepo.findAll(Sort.by(Sort.Direction.DESC, "likes")).stream()
        .limit(5)
        .collect(Collectors.toList());

    }


    public Post postDtoToPost(PostDTO postDTO){
        Post post = new Post();
        post.setContent(postDTO.getContent());
        post.setCreatedAt(postDTO.getCreatedAt());
        post.setId(postDTO.getId());
        post.setLikes(postDTO.getLikes());
        post.setUpdatedAt(postDTO.getUpdatedAt());
        post.setUser(postDTO.getUser());

        return post;
    }

    public PostDTO postToPostDTO(Post post){
        PostDTO postDTO = new PostDTO();
        postDTO.setContent(post.getContent());
        postDTO.setCreatedAt(post.getCreatedAt());
        postDTO.setId(post.getId());
        postDTO.setLikes(post.getLikes());
        postDTO.setUpdatedAt(post.getUpdatedAt());
        postDTO.setUser(post.getUser());

        return postDTO;
    }
    
}
