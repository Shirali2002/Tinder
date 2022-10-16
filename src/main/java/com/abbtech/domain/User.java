package com.abbtech.domain;

import com.abbtech.dto.PpLink;
import com.abbtech.exceptions.NoSuchUserException;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;

@Data
public class User {
  private int id;
  private String username;
  private PpLink link;

  public User(int id, String username, int linkId) {
    this.id = id;
    this.username = username;
    this.link = getPpLink(linkId);
  }

  private PpLink getPpLink(int linkId) {
    return Arrays.stream(PpLink.values())
        .filter(p -> p.getId() == linkId)
        .findFirst()
        .orElseThrow(NoSuchUserException::new);
  }

}