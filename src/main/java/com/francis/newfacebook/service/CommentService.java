package com.francis.newfacebook.service;

import com.francis.newfacebook.model.Comment;
import com.francis.newfacebook.model.Post;
import com.francis.newfacebook.model.Users;
import com.francis.newfacebook.repository.CommentRepository;
import com.francis.newfacebook.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PostRepository postRepository;

    public Iterable<Comment> getAllComment() {
        return commentRepository.findAll();
    }
    public void saveComment(Comment comment, Long postId) {
        Comment commentDB = commentRepository.save(comment);
        Optional<Post> post = postRepository.findById(postId);
        List<Comment> comments = post.get().getComments();
        comments.add(commentDB);
        post.get().setComments(comments);
        postRepository.save(post.get());
//        Post post = postRepository.findById(postId).get();
//        comment.setPost(post);
//        commentRepository.save(comment);
    }
    public Comment getCommentById(Long commentId) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        return comment.get();
    }
    public void updateComment(Comment editedComment) {
        commentRepository.save(editedComment);
    }
    public void deleteComment(Long commentId, Long postID) {
        Post post =  postRepository.findById(postID).get();
        Comment comment = commentRepository.findById(commentId).get();
        post.getComments().remove(comment);
        commentRepository.deleteById(commentId);
    }
}
