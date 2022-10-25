package com.abbtech.service;

import com.abbtech.domain.Message;
import com.abbtech.repository.MessageRepository;

import java.util.List;
import java.util.Optional;

public class MessageService {
  MessageRepository messageRepo = new MessageRepository();

  public List<Message> getAll() {
    return messageRepo.getAll();
  }

  public Optional<Message> getById(int id) {
    return messageRepo.getById(id);
  }

  public boolean add(Message value) {
    return messageRepo.add(value);
  }

  public boolean delete(int id) {
    return messageRepo.delete(id);
  }
}
