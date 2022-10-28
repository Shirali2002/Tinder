package com.abbtech.service;

import com.abbtech.domain.User;
import com.abbtech.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserService {
  private final UserRepository userRepo = new UserRepository();

  public Optional<User> login(String username, String password) {
    return userRepo.getByUsernameAndPassword(username, password);
  }

  public Optional<User> register(User user) {
    Optional<User> userOptional = userRepo.getByUsername(user.getUsername());
    if (userOptional.isEmpty()) {
      userRepo.add(user);
    }
    return userOptional;
  }

  public void setLastSeen(int id) {
    userRepo.setLastSeen(id);
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

}