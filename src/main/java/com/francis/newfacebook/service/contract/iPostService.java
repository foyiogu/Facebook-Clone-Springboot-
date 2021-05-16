package com.francis.newfacebook.service.contract;

import com.francis.newfacebook.model.Post;
import com.francis.newfacebook.model.Users;

import java.util.List;

public interface iPostService {
    List<Post> listAllPosts();
    void addPost(String body, Users user);
    Post getPosts(Long id);
    void deleteAPost(Long id);
    void updatePost(Long postId, Post post);
}
