package com.abbtech.service;

import com.abbtech.domain.Message;
import com.abbtech.repository.MessageRepository;

import java.util.Collections;
import java.util.List;

public class MessageService {

  MessageRepository messageRepo = new MessageRepository();

  public List<Message> getBySenderAndReceiverId(int senderId, int receiverId) {
    List<Message> messages = messageRepo.getBySenderAndReceiverId(senderId, receiverId);
    messages.sort(Message.comparatorBySendDate);
    return messages;
  }

  public boolean add(Message value) {
    return messageRepo.add(value);
  }

}