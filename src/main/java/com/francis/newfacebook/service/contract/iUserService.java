package com.francis.newfacebook.service.contract;

import com.francis.newfacebook.model.Users;

public interface iUserService {
    void registerNewUser(Users user);
    Users getUser(String email, String password);
}
