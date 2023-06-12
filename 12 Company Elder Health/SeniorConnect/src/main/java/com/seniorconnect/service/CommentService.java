package com.seniorconnect.service;

import java.util.List;

import com.seniorconnect.entities.Comment;

public interface CommentService {
	Comment createComment(Comment commentDto, Long postId, Long userId);
    Comment updateComment(Long commentId, Comment commentDto);
    void deleteComment(Long commentId);
    Comment getCommentById(Long commentId);
    List<Comment> getCommentsByPost(Long postId);
    int getCommentCountByPost(Long postId);
}
