package com.francis.newfacebook.controller;

import com.francis.newfacebook.model.Post;
import com.francis.newfacebook.model.Users;
import com.francis.newfacebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginSignUpController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String showLoginPage(Model model) {
        model.addAttribute("user", new Users());
        return "index";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user")Users user, HttpServletRequest request, RedirectAttributes redirectAttributes){
        Post newPost = new Post();

        //For Validation of new user
        Users validUser = userService.getUser(user.getEmail(),user.getPassword());
//        System.out.println(person);
        if (validUser == null) {
            redirectAttributes.addFlashAttribute("not_found", "Email or password not valid, please check again or sign up");
            return "index";
        }else{
            request.getSession().invalidate();
            HttpSession session = request.getSession(true);
            session.setMaxInactiveInterval(1000);
            session.setAttribute("user_session",validUser);
            return "redirect:/newsfeed";
        }
    }

    //FOR SIGNING UP
    @GetMapping("/signup")
    public String showSignUpPage(Model model){
        model.addAttribute("user", new Users());
        return "signup";
    }

    @PostMapping("/signupPost")
    public String reg(@ModelAttribute("user")Users user){
        userService.registerNewUser(user);
        return "index";
    }

    //FOR LOG OUT

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
