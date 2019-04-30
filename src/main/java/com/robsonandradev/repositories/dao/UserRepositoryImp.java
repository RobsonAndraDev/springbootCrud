package com.robsonandradev.repositories.dao;

import com.robsonandradev.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.List;

public class UserRepositoryImp implements UserRepository {
  @PersistenceContext
  private EntityManager em;

  @Override
  public List<User> userExists(String username, String password) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<User> query = cb.createQuery(User.class);
    Root<User> user = query.from(User.class);
    query.select(user);

    return em.createQuery(query).getResultList();
  }

  @Override
  public <S extends User> S save(S s) {
    return null;
  }

  @Override
  public <S extends User> Iterable<S> save(Iterable<S> iterable) {
    return null;
  }

  @Override
  public User findOne(Integer integer) {
    return null;
  }

  @Override
  public boolean exists(Integer integer) {
    return false;
  }

  @Override
  public Iterable<User> findAll() {
    return null;
  }

  @Override
  public Iterable<User> findAll(Iterable<Integer> iterable) {
    return null;
  }

  @Override
  public long count() {
    return 0;
  }

  @Override
  public void delete(Integer integer) {

  }

  @Override
  public void delete(User user) {

  }

  @Override
  public void delete(Iterable<? extends User> iterable) {

  }

  @Override
  public void deleteAll() {

  }
}
