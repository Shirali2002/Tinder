package com.abbtech.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Message {
  private int id;
  private String message;
}
