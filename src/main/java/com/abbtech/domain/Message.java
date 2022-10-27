package com.abbtech.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

@Data
@AllArgsConstructor
public class Message {

  private int id;
  private String message;
  private LocalDateTime sendDate;
  private int fromUser;
  private int toUser;

  public static final Comparator<Message> comparatorBySendDate =
      Comparator.comparing(p -> p.sendDate);

  public Message(String message, Timestamp sendDate, int fromUser, int toUser) {
    this.message = message;
    this.sendDate = sendDate.toLocalDateTime();
    this.fromUser = fromUser;
    this.toUser = toUser;
  }

  public String getSendDateAsString() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    return sendDate.format(formatter);
  }

}