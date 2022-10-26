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

  public Optional<User> login(String username, String password) {
    List<User> allUser = userRepo.getAll();
    return allUser.stream()
        .filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password))
        .findFirst();
  }

  public String register(User user) {
    List<User> allUser = userRepo.getAll();
    Optional<User> user1 = allUser.stream()
        .filter(u -> u.getUsername().equals(user.getUsername())).findFirst();
    if (user1.isPresent()) {
      return "Registration is failed";
    } else {
      allUser.add(user);
      return "Registration us succesfull";
    }

  }

  public String updatePassword(User user) {
    List<User> allUser = userRepo.getAll();
    Optional<User> user1 = allUser.stream()
        .filter(u -> u.getUsername().equals(user.getUsername())).findFirst();
    if (user1.isPresent()) {
      user1.get().setPassword(user.getPassword());
      return "Password could be changed succesfully";
    } else {
      return "Password could not be changed";
    }
  }

  public Optional<User> findByUsername(String username) {
    List<User> allUser = userRepo.getAll();
    Optional<User> userByName = allUser.stream().filter(u -> u.getUsername().equals(username)).findAny();
    if (userByName.isPresent()) {
      return userByName;
    } else {
      return Optional.empty();
    }
  }

  public List<User> getAll() {
    return userRepo.getAll();
  }

  public Optional<User> getById(int id) {
    return userRepo.getById(id);
  }

  public boolean add(User value) {
    return userRepo.add(value);
  }

  public boolean delete(int id) {
    return userRepo.delete(id);
  }
}
