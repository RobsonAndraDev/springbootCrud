package com.robsonandradev.services;

import com.robsonandradev.entities.User;

import java.util.List;

public interface UserService {

    List<User> userExists(String username, String password);

}
