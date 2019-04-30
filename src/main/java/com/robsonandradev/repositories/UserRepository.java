package com.robsonandradev.repositories;

import com.robsonandradev.entities.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UserRepository {

  @PersistenceContext
  private  EntityManager em;

  public boolean userExists(User user) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<User> criteriaQuery = cb.createQuery(User.class);
    @SuppressWarnings("unused")
    Root<User> root = criteriaQuery.from(User.class);
    criteriaQuery.where(
        cb.equal(root.get("login"), user.getLogin()),
        cb.equal(root.get("password"), user.getPassword())
    );
    List users = em.createQuery(criteriaQuery).getResultList();
    System.out.println(users);
    if (users.isEmpty()) {
      return false;
    }
    return true;
  }
}
