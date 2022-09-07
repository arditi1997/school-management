package com.schoolManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schoolManagement.model.Post;
import com.schoolManagement.model.User;

public interface PostRepository extends JpaRepository<Post, Integer>{
	
	public List<Post> findByUser(User user);

}
