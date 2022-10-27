package com.abbtech.service;

import com.abbtech.domain.Like;
import com.abbtech.domain.User;
import com.abbtech.repository.LikeRepository;
import com.abbtech.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LikeService {

  LikeRepository likeRepository = new LikeRepository();
  UserRepository userRepository = new UserRepository();

  public List<Like> getAll() {
    return likeRepository.getAll();
  }

  public List<Like> getUserLikes(int fromId) {
    return likeRepository.getUserLikes(fromId);
  }

  public List<User> getLikedUsers(int fromId) {
    List<Like> likes = likeRepository.getUserLikes(fromId);
    List<User> users = new ArrayList<>();
    for (Like l : likes) {
      userRepository
          .getById(l.getToWhom())
          .ifPresent(users::add);
    }
    return users;
  }

  public boolean add(Like value) {
    Optional<Like> like = likeRepository.getAll().stream()
        .filter(l -> l.getToWhom() == value.getToWhom() && l.getFromWhom() == value.getFromWhom())
        .findFirst();
    if (like.isEmpty()) return likeRepository.add(value);
    else return false;
  }

  public boolean remove(Like value) {
    return likeRepository.remove(value);
  }

}