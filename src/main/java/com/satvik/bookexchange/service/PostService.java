package com.satvik.bookexchange.service;

import com.satvik.bookexchange.entity.Community;
import com.satvik.bookexchange.entity.Post;
import com.satvik.bookexchange.entity.User;
import com.satvik.bookexchange.pojo.PostAddRequest;
import com.satvik.bookexchange.pojo.PostDto;
import com.satvik.bookexchange.repository.CommunityRepository;
import com.satvik.bookexchange.repository.PostRepository;
import com.satvik.bookexchange.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashSet;
import java.util.NoSuchElementException;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommunityRepository communityRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ResponseEntity<?> createPost(PostAddRequest postAddRequest) {

        User user = userRepository.findById(postAddRequest.getUserId()).orElseThrow(NoSuchElementException::new);
        Community community = communityRepository.findById(postAddRequest.getCommunityId()).orElseThrow(NoSuchElementException::new);

        Post newPost = Post.builder()
                .description(postAddRequest.getDescription())
                .title(postAddRequest.getTitle())
                .type(postAddRequest.getType().getValue())
                .creator(user)
                .community(community)
                .build();

        Post savedPost = postRepository.save(newPost);

        return new ResponseEntity<>(postToDto(savedPost), HttpStatus.CREATED);
    }

    private PostDto postToDto(Post post){
        return modelMapper.map(post, PostDto.class);
    }

    public ResponseEntity<?> deletePost(int id) {
        postRepository.deleteById(id);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }
}
