package com.francis.newfacebook.service;

import com.francis.newfacebook.model.Like;
import com.francis.newfacebook.model.Post;
import com.francis.newfacebook.model.Users;
import com.francis.newfacebook.repository.LikeRepository;
import com.francis.newfacebook.service.contract.iLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeService implements iLikeService {

    @Autowired
    private LikeRepository likeRepository;

    public Like getPostLikeByPostAndUser(Post post, Users user) {
        return likeRepository.getPostLikeByPostAndAndUser(post, user);
    }

    public void addLike(Like like) {
        likeRepository.save(like);
    }

    public void deleteLike(Like like) {
        likeRepository.delete(like);
    }

    public void deleteAllPostLike(Post post, Users user) {
        likeRepository.deleteAllByPostAndUser(post, user);
    }

    @Override
    public int totalNumberOfLikes(Long id) {
        return likeRepository.countLikeByPostId(id);
    }

}