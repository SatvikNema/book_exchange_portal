package com.satvik.bookexchange.controller;

import com.satvik.bookexchange.pojo.PostAddRequest;
import com.satvik.bookexchange.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/post")
    public ResponseEntity<?> createPost(@RequestBody PostAddRequest postAddRequest){
        return postService.createPost(postAddRequest);
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity<?> deletePost(@PathVariable int id){
        return postService.deletePost(id);
    }
}
