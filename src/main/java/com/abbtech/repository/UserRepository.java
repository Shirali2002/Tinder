package com.abbtech.repository;

import com.abbtech.domain.User;
import com.abbtech.repository.db.AbstractConnection;
import com.abbtech.repository.db.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository extends AbstractConnection implements DAO<User> {
  @Override
  public List<User> getAll() {
    List<User> users = new ArrayList<>();
    try (Connection c = connection()) {
      Statement statement = c.createStatement();
      statement.execute("SELECT u.id," +
          " u.username," +
          " u.idpp " +
          "FROM users u");
      ResultSet rs = statement.getResultSet();
      while (rs.next()) {
        users.add(createUser(rs));
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    return users;
  }

  @Override
  public Optional<User> getById(int id) {
    User result = null;
    try (Connection c = connection()) {
      PreparedStatement statement = c.prepareStatement
          ("SELECT u.id," +
              " u.username," +
              " u.idpp " +
              "FROM users u" +
              "where u.id = ?");
      statement.setInt(1, id);
      statement.execute();
      ResultSet rs = statement.getResultSet();
      while (rs.next()) {
        result = createUser(rs);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    return Optional.ofNullable(result);
  }

  @Override
  public boolean add(User value) {
//    try (Connection connection = connection())
//    {
//      PreparedStatement preparedSt = connection.prepareStatement();
//    } catch (Exception e) {
//      throw new RuntimeException(e);
//      return false;
//    }
return false;
  }

  @Override
  public boolean delete(User value) {
    return false;
  }

  private User createUser(ResultSet rs) throws SQLException {
    return new User(rs.getInt("id"),
        rs.getString("username"),
        rs.getInt("idpp"));
  }
}
