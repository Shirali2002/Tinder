package com.abbtech.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class User {

  private int id;
  private String username;
  private String password;
  private LocalDateTime lastSeen;
  private String link;

  public User(int id, String username, String password,LocalDateTime lastseen, String link) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.lastSeen = lastseen;
    this.link = link;
  }

  public User() {

  }
}