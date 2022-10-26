package com.abbtech.service;

import com.abbtech.domain.User;
import com.abbtech.repository.UserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public class UserService {
  private final UserRepository userRepo = new UserRepository();


  public Optional<User> findByUsernameAndPassword(String username, String password) {
    return userRepo.findByUsernameAndPassword(username, password);
  }

  public Optional<User> findByUsername(String username) {
    return userRepo.findByUsername(username);
  }
  public void setLastLogin(int id){
    userRepo.setLastLogin(id);
  }

  public List<User> findAll() {
    return userRepo.findAll();
  }

  public Optional<User> findById(int id) {
    return userRepo.findById(id);
  }

  public boolean add(User value) {
    return userRepo.add(value);
  }

  public boolean delete(int id) {
    return userRepo.delete(id);
  }
}
