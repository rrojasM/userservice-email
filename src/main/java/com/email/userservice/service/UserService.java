package com.email.userservice.service;

import com.email.userservice.domain.User;

public interface UserService {
    User saveUser(User user);
    Boolean verifyToken(String token);
}
