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

  //  public String updatePassword(User user) {
//    List<User> allUser = userRepo.getAll();
//    Optional<User> user1 = allUser.stream()
//        .filter(u -> u.getUsername().equals(user.getUsername())).findFirst();
//    if (user1.isPresent()) {
//      user1.get().setPassword(user.getPassword());
//      return "Password could be changed succesfully";
//    } else {
//      return "Password could not be changed";
//    }
//  }
//
//  public Optional<User> findByUsername(String username) {
//    List<User> allUser = userRepo.getAll();
//    Optional<User> userByName = allUser.stream().filter(u -> u.getUsername().equals(username)).findAny();
//    if (userByName.isPresent()) {
//      return userByName;
//    } else {
//      return Optional.empty();
//    }
//  }
//
  public List<User> getAll() {
    return userRepo.getAll();
  }

  public Optional<User> getById(int id) {
    return userRepo.getById(id);
  }

  public boolean add(User value) {
    return userRepo.add(value);
  }

//  public boolean delete(int id) {
//    return userRepo.delete(id);
//  }
}