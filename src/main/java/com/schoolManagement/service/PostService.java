package com.schoolManagement.service;

import com.schoolManagement.model.Post;
import com.schoolManagement.model.User;

import java.util.List;

public interface PostService {
    void save(Post post);

    Post getOne(int postId);

    List<Post> findByUser(User user);

    List<Post> findAll();
}
