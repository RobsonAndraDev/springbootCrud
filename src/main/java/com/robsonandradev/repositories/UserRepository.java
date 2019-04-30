package com.robsonandradev.repositories;

import com.robsonandradev.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

  @Query(
    value = "SELECT * FROM user WHERE login = ':username' AND password = ':password'", nativeQuery = true,
    countQuery = "SELECT COUNT(*) FROM user WHERE login = ':username' AND password = ':password'"
  )
  List<User> userExists(@Param("username") String username, @Param("password") String password);
}
