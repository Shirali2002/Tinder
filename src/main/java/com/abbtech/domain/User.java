package com.abbtech.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class User {

  private int id;
  private String username;
  private String password;
  private LocalDateTime lastSeen;
  private String link;

  public User(int id, String username, String password, LocalDateTime lastseen, String link) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.lastSeen = lastseen;
    this.link = link;
  }

  public String getLastSeenAsString() {
    if (lastSeen == null) {
      return "did not logged in";
    }
    DateTimeFormatter formatter;
    if (lastSeen.getYear() == LocalDateTime.now().getYear()
        && lastSeen.getDayOfYear() == LocalDateTime.now().getDayOfYear()) {
      formatter = DateTimeFormatter.ofPattern("HH:mm");
      return "today " + lastSeen.format(formatter);
    } else {
      formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
      return lastSeen.format(formatter);
    }
  }
}