package com.abbtech.repository.db;

import java.util.List;
import java.util.Optional;

public interface DAO<A> {
  List<A> getAll();
  Optional<A> getById(int id);
  boolean add(A value);
  boolean delete(int id);



}
