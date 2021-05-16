package com.francis.newfacebook.controller;

import com.francis.newfacebook.model.Comment;
import com.francis.newfacebook.model.Like;
import com.francis.newfacebook.model.Post;
import com.francis.newfacebook.model.Users;
import com.francis.newfacebook.repository.CommentRepository;
import com.francis.newfacebook.service.CommentService;
import com.francis.newfacebook.service.LikeService;
import com.francis.newfacebook.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class PostController {

     PostService postService;

    CommentService commentService;

    LikeService likeService;
    CommentRepository commentRepository;

    @Autowired
    public PostController(PostService postService, CommentService commentService, LikeService likeService, CommentRepository commentRepository) {
        this.postService = postService;
        this.commentService = commentService;
        this.likeService = likeService;
        this.commentRepository = commentRepository;
    }

    @GetMapping("/newsfeed")
    public String viewPostPage(Model model){
        //create a list from the db
        Post post = new Post();
        List<Post> listPosts = postService.listAllPosts();
        Iterable<Comment> comments = commentService.getAllComment();
//        int total = listPosts.size();
        //set the post list to modal
        model.addAttribute("listPosts", listPosts);
        model.addAttribute("comment", new Comment());
        model.addAttribute("comments", comments);
        model.addAttribute("newPost",post);
//        model.addAttribute("totalLikesOnPost", total);
        model.addAttribute("newPostLike", new Like());

        //return the page
        return "newsfeed";
    }

    @PostMapping("/saveNewPost")
    public String savePost(@ModelAttribute("newPost") Post newPost, HttpServletRequest request){
        Users loggedInUser = (Users) request.getSession().getAttribute("user_session");
        postService.addPost(newPost.getPostBody(), loggedInUser);
        return "redirect:/newsfeed";
    }

    @RequestMapping(path = "/delete/{id}")
    public String deleteAPost(@PathVariable(name = "id")Long id, HttpServletRequest request ){
        Users loggedInUser = (Users) request.getSession().getAttribute("user_session");
        Post post = postService.getPosts(id);
        if (loggedInUser.getId() == post.getUser().getId()) {
            postService.deleteAPost(id);
            return "redirect:/newsfeed";
        }
        return "redirect:/newsfeed";
    }

    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable(name = "id")Long id, Model model, HttpServletRequest request) {
        Users loggedInUser = (Users) request.getSession().getAttribute("user_session");
        Post post = postService.getPosts(id);
        if (loggedInUser.getId() == post.getUser().getId()) {
            model.addAttribute("newPost", post);
            //temp
            return "edit";
        }
        return "redirect:/newsfeed";
    }

    @PostMapping("/edit_post")
    public String editPost(@ModelAttribute("newPost")Post post){
        postService.updatePost(post.getId(), post);
        return "redirect:/newsfeed";
    }



    @PostMapping("/like/{id}")
    public String likeIndex(@PathVariable("id") Long id, HttpSession session, Model model) {
        Users userObj = (Users) session.getAttribute("user_session");
        Post post = postService.getPosts(id);
        Like newPostLike = likeService.getPostLikeByPostAndUser(post,userObj);

        model.addAttribute("newPostLike", newPostLike);
        return "redirect:/newsfeed";
    }

    @PostMapping("/comment/{id}")
    public String addComment(@ModelAttribute("comment") Comment comment, @PathVariable(name = "id") Long postId) {
        commentService.saveComment(comment, postId);
        return "redirect:/newsfeed";
    }
    @GetMapping("/update/{id}")
    public String editComment(@PathVariable(name = "id") Long commentId, Model model) {
        Comment comment = commentService.getCommentById(commentId);
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
        commentService.deleteComment(commentId, postId);
        return "redirect:/newsfeed";
    }


}
