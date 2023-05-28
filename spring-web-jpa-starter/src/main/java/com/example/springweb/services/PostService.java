package com.example.springweb.services;

import com.example.springweb.entities.Post;
import com.example.springweb.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public Post savePost(Post post) {
        Post savedPost = repository.save(post);
        return savedPost;
    }
}
