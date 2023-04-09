package com.socialmedia.dto;

import java.time.LocalDateTime;

import com.socialmedia.model.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostDTO {
    
    private Long id;
    
    private User user;
    
    private String content;
   
    private LocalDateTime createdAt;
   
    private LocalDateTime updatedAt;
    
    private int likes;
}
