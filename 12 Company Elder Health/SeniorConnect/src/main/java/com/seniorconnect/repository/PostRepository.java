package com.seniorconnect.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.seniorconnect.entities.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	Optional<Post> findById(Long postId);
	// gets a Post object by its postId

    List<Post> findByCreatedBy(Long userId);
    //Gets a list of Post entities created by the user with the given userId
    
    long countByCreatedBy(Long userId);
	@Query("SELECT p FROM Post p WHERE p.user.id = :userId")
    List<Post> findByUserIdCustomQuery(@Param("userId") Long userId);
	//counts the number of Post entities created by the user with the given userId
}
