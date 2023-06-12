package com.seniorconnect.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.seniorconnect.entities.Post;
import com.seniorconnect.exception.ResourceNotFoundException;
import com.seniorconnect.repository.PostRepository;
import com.seniorconnect.repository.SavedPostRepository;
import com.seniorconnect.service.PostService;

@Service
public class PostServiceImpl implements PostService{
	private PostRepository postRepository;
	private SavedPostRepository savedPostRepository;

    @Autowired
    public void PostServiceImplementation(PostRepository postRepository, SavedPostRepository savedPostRepository) {
        this.postRepository = postRepository;
        this.savedPostRepository =savedPostRepository;
    }

    @Override
    public Post createPost(Post postDto, Long userId) {
        // Set the user who created the post
    	savedPostRepository.saveNewSavedPost(postDto.getPostId(), userId);
        return postRepository.save(postDto);
    }

    @Override
    public Post updatePost(Long postId, Post postDto) {
        Post existingPost = getPostById(postId);
        existingPost.setDescription(postDto.getDescription());
        existingPost.setImagePath(postDto.getImagePath());
        existingPost.setUpdatedDate(LocalDateTime.now());
        return postRepository.save(existingPost);
    }

    @SuppressWarnings("deprecation")
	@Override
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public Post getPostById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + postId));
    }

    @Override
    public List<Post> getPostsByUser(Long userId) {
        return postRepository.findByCreatedBy(userId);
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public int getPostCountByUser(Long userId) {
        return Math.toIntExact(postRepository.countByCreatedBy(userId));
    }
}
