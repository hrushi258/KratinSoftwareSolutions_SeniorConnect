package com.seniorconnect.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.seniorconnect.entities.Like;
import com.seniorconnect.entities.Post;
import com.seniorconnect.entities.User;
import com.seniorconnect.exception.ResourceNotFoundException;
import com.seniorconnect.repository.LikeRepository;
import com.seniorconnect.repository.PostRepository;
import com.seniorconnect.repository.UserRepository;
import com.seniorconnect.service.LikeService;
@Service
public class LikeServiceImpl implements LikeService {
	private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public LikeServiceImpl(LikeRepository likeRepository, PostRepository postRepository, UserRepository userRepository) {
        this.likeRepository = likeRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Like addLike(Long postId, Long userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + postId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        
        Like like = new Like();
        like.setPost(post);
        like.setUser(user);
        
        return likeRepository.save(like);
    }

    @Override
    public void removeLike(Long likeId) {
        likeRepository.deleteById(likeId);
    }

    @SuppressWarnings("deprecation")
	@Override
    public List<Like> getLikesByPost(Long postId) {
        return likeRepository.findByPost(postRepository.getById(postId));
    }

    @Override
    public List<Like> getLikesByUser(Long userId) {
        return likeRepository.findByUser(userRepository.findById(userId).get());
    }

    @Override
    public boolean checkIfLikedByUser(Long postId, Long userId) {
        return likeRepository.existsByPostAndUser(postRepository.findById(postId).get(), userRepository.findById(userId).get());
    }

    @Override
    public long getLikeCountByPost(Long postId) {
        return likeRepository.countByCreatedBy(postId);
    }

}
