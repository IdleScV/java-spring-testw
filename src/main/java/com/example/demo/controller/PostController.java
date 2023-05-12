package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.service.PostService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;
    private final UserService userService;
    
    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping
    public List<Post> getPosts() {
        return postService.getPosts();
    }

    @PostMapping
    public Post createPost(@RequestBody Post post, @RequestParam Long userId) {
    post.setContent(null);
    User author = userService.getUserById(userId);
    return postService.createPost(post, author);
    }

    @GetMapping("/{postId}")
    public Post getPostById(@PathVariable Long postId) {
        return postService.getPostById(postId);
    }

    @PutMapping("/{postId}")
    public Post updatePost(@PathVariable Long postId, @RequestBody Post updatedPost) {
        return postService.updatePost(postId, updatedPost);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
    }
}
