package com.socialmedia.dto;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {

    private Long id;
    
    private String name;

    private String email;
    
    private String bio;
 
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;

    private Long postCount;
}
