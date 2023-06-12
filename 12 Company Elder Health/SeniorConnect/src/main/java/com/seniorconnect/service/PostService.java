package com.seniorconnect.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.seniorconnect.entities.Post;

@Service
public interface PostService {
	Post createPost(Post postDto, Long userId);
    Post updatePost(Long postId, Post postDto);
    void deletePost(Long postId);
    Post getPostById(Long postId);
    List<Post> getPostsByUser(Long userId);
    List<Post> getAllPosts();
    int getPostCountByUser(Long userId);
}
