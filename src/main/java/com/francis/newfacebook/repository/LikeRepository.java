package com.francis.newfacebook.repository;

import com.francis.newfacebook.model.Like;
import com.francis.newfacebook.model.Post;
import com.francis.newfacebook.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    Like getPostLikeByPostAndAndUser(Post post, Users user);
    int countLikeByPostId(Long id);
    void deleteAllByPostAndUser(Post post, Users user);
}
