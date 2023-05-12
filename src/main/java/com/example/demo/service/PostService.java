package com.example.demo.service;

import com.example.demo.model.Post;
import com.example.demo.model.User;

import com.example.demo.repository.PostRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

@Service
public class PostService {

     private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public Post createPost(Post post, User author) {
        // Parse the Markdown content and convert it to HTML
        post.setAuthor(author);
        String markdownContent = post.getRawContent();
        Parser parser = Parser.builder().build();
        Node document = parser.parse(markdownContent);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        String htmlContent = renderer.render(document);

        // Set the HTML content to the post
        post.setContent(htmlContent);

        return postRepository.save(post);
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    public Post updatePost(Long postId, Post updatedPost) {
        Post existingPost = postRepository.findById(postId).orElse(null);
        if (existingPost != null) {
            existingPost.setTitle(updatedPost.getTitle());
            existingPost.setRawContent(updatedPost.getRawContent());
            // Set other fields to be updated as needed
            return postRepository.save(existingPost);
        }
        return null;
    }

}
