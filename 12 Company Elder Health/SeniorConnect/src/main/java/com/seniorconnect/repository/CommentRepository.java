package com.seniorconnect.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.seniorconnect.entities.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT COUNT(v.id) from Comment v where v.post.id = :postId")
    long countByPostId(@Param("postId") Long postId);
    //counts the number of comments associated with a specific post

    List<Comment> findCommentsByPostId(@Param("postId") Long postId);
    //gets a List<Comment> containing comments

    @Query("SELECT c FROM Comment c where c.id = :commentId and c.post.id = :postId")
    Optional<Comment> findCommentByPostIdAndCommentId(@Param("postId") Long postId, @Param("commentId") Long commentId);
  //a specific comment associated with a post identified by the given postId and the comment identified by the given commentId
}

