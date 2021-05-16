package com.francis.newfacebook.service.contract;

import com.francis.newfacebook.model.Like;
import com.francis.newfacebook.model.Post;
import com.francis.newfacebook.model.Users;

import java.util.List;

public interface iLikeService {

    Like getPostLikeByPostAndUser(Post post, Users user);
    void addLike(Like like);
    void deleteLike(Like like);
    void deleteAllPostLike(Post post, Users user);
    int totalNumberOfLikes(Long id);
}
