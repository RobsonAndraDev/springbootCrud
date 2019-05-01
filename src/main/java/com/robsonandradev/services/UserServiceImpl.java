package com.robsonandradev.services;


import com.robsonandradev.entities.User;
import com.robsonandradev.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> userExists(String username, String password) {
        return userRepository.userExists(username, password);
    }
}
