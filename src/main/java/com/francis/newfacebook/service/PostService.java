package com.francis.newfacebook.service;

import com.francis.newfacebook.model.Post;
import com.francis.newfacebook.model.Users;
import com.francis.newfacebook.repository.PostRepository;
import com.francis.newfacebook.service.contract.iPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PostService implements iPostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Post> listAllPosts() {
        return postRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }

    @Override
    public void addPost(String postBody, Users user) {
        Post post = new Post();
        post.setPostBody(postBody);
        post.setUser(user);
        postRepository.save(post);
    }

    @Override
    public Post getPosts(Long id) {
        return postRepository.findById(id).get();
    }

    @Override
    public void deleteAPost(Long id) {
        this.postRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updatePost(Long postId, Post post) {
        Post postDB = this.postRepository.findById(postId)
                .orElseThrow(()-> new IllegalStateException("this does not exist"));
        String postBody = post.getPostBody();
        if (postBody != null && postBody.length()>0 && !Objects.equals(postDB.getPostBody(),postBody)){
            postDB.setPostBody(postBody);
            postRepository.save(postDB);
        }
    }
}
