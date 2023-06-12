package com.seniorconnect.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.seniorconnect.entities.SavedPost;
	
	@Repository
	public interface SavedPostRepository extends JpaRepository<SavedPost, Long> {
		@Query("SELECT p FROM SavedPost p where p.post.id = :postId and p.user.id = :userId")
	    Optional<SavedPost> findSavedPostByPostIdByUserId(@Param("userId") Long userId, @Param("postId") Long postId);
		//gets a saved post based on the given userId and postId
		
	    List<SavedPost> findByCreatedBy(Long userId);
	    //gets a list of saved posts based on the userId who who created the saved posts
	    
	    @Modifying
	    @Query("INSERT INTO SavedPost (user, post) VALUES (:userId, :postId)")
	    void saveNewSavedPost(@Param("userId") Long userId, @Param("postId") Long postId);
	}
