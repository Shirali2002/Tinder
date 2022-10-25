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

}