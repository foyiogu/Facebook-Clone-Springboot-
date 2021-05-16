package com.francis.newfacebook.service;

import com.francis.newfacebook.model.Users;
import com.francis.newfacebook.repository.UserRepository;
import com.francis.newfacebook.service.contract.iUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements iUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void registerNewUser(Users user) {
        Optional<Users> userOptional = userRepository.findUserByEmail(user.getEmail());
        if (userOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }
        userRepository.save(user);
    }

    @Override
    public Users getUser(String email, String password) {
       Users user = this.userRepository.findUserByEmailAndPassword(email, password);
        return user;
    }
}
