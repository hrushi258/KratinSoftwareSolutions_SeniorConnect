package com.seniorconnect.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seniorconnect.entities.Like;
import com.seniorconnect.entities.Post;
import com.seniorconnect.entities.User;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
	
	List<Like> findByPost(Post post);//gets all likes associated with a specific post

	List<Like> findByUser(User user);//gets all likes made by a specific user

	long countByCreatedBy(Long userId);//counts the number of likes for a post

	boolean existsByPostAndUser(Post post, User user);
}
