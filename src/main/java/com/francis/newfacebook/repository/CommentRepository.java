package com.francis.newfacebook.repository;

import com.francis.newfacebook.model.Comment;
import com.francis.newfacebook.model.Post;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findCommentsByPost(Post post);
}
