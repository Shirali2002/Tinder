package com.abbtech.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;

@AllArgsConstructor
public enum PpLink {

  FIRST(1, "abb"),
  SECOND(2,"abb"),
  THIRD(3,"abb");

  private final int id;
  private final String link;

  public int getId() {
    return id;
  }

  public String getLink() {
    return link;
  }

}
