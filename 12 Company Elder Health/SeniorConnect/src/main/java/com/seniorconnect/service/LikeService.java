package com.seniorconnect.service;

import java.util.List;

import com.seniorconnect.entities.Like;

public interface LikeService {
	Like addLike(Long postId, Long userId);
    void removeLike(Long likeId);
    List<Like> getLikesByPost(Long postId);
    List<Like> getLikesByUser(Long userId);
    boolean checkIfLikedByUser(Long postId, Long userId);
    long getLikeCountByPost(Long postId);
}
