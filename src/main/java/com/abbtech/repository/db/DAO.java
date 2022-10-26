package com.abbtech.repository.db;

import java.util.List;
import java.util.Optional;

public interface DAO<A> {
  List<A> findAll();
  Optional<A> findById(int id);
  boolean add(A value);
  boolean delete(int id);



}
