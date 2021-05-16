package com.francis.newfacebook.controller;

import com.francis.newfacebook.model.Comment;
import com.francis.newfacebook.repository.CommentRepository;
import com.francis.newfacebook.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

public class CommentController {

    @Autowired
    private CommentService commentServiceImplementation;

    @Autowired
    private CommentRepository commentRepository;

    @PostMapping("/comment/{id}")
    public String addComment(@ModelAttribute("comment") Comment comment, @PathVariable(name = "id") Long postId) {
        commentServiceImplementation.saveComment(comment, postId);
        return "redirect:/newsfeed";
    }
    @GetMapping("/update/{id}")
    public String editComment(@PathVariable(name = "id") Long commentId, Model model) {
        Comment comment = commentServiceImplementation.getCommentById(commentId);
        model.addAttribute("comment", comment);
        return "editComment";
    }
    @PostMapping("/update")
    public String editComment(@ModelAttribute("comments") Comment editedComment) {
        Optional<Comment> comment = commentRepository.findById(editedComment.getId());
        comment.get().setCommentBody(editedComment.getCommentBody());
        commentRepository.save(editedComment);
        return "redirect:/newsfeed";
    }
    @GetMapping("/deleteComment/{id}/{postID}")
    public String deleteComment(@PathVariable(name = "id") Long commentId, @PathVariable(name = "postID") Long postId) {
        commentServiceImplementation.deleteComment(commentId, postId);
        return "redirect:/newsfeed";
    }
}
