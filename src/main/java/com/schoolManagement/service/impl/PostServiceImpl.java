package com.schoolManagement.service.impl;

import com.schoolManagement.model.Post;
import com.schoolManagement.model.User;
import com.schoolManagement.repository.PostRepository;
import com.schoolManagement.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public void save(Post post) {
        postRepository.save(post);
    }

    @Override
    public Post getOne(int postId) {
        return postRepository.getOne(postId);
    }

    @Override
    public List<Post> findByUser(User user) {
        return postRepository.findByUser(user);
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }
}
