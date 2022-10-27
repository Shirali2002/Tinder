package com.abbtech.repository;

import com.abbtech.domain.User;
import com.abbtech.repository.db.AbstractConnection;
import com.abbtech.repository.db.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository extends AbstractConnection implements DAO<User> {

  @Override
  public List<User> getAll() {
    List<User> users = new ArrayList<>();
    try (Connection c = connection()) {
      Statement statement = c.createStatement();
      statement.execute("SELECT * FROM user u");
      ResultSet rs = statement.getResultSet();
      while (rs.next()) {
        users.add(createUser(rs));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return users;
  }


  @Override
  public Optional<User> getById(int id) {
    User result = null;
    try (Connection c = connection()) {
      PreparedStatement statement = c.prepareStatement("SELECT * FROM user u where u.id = ?");
      statement.setInt(1, id);
      statement.execute();
      ResultSet rs = statement.getResultSet();
      while (rs.next()) {
        result = createUser(rs);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return Optional.ofNullable(result);
  }

  public Optional<User> getByUsername(String username) {
    User result = null;
    try (Connection c = connection()) {
      PreparedStatement statement = c.prepareStatement("SELECT * FROM user u where u.username = ?");
      statement.setString(1, username);
      statement.execute();
      ResultSet rs = statement.getResultSet();
      while (rs.next()) {
        result = createUser(rs);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return Optional.ofNullable(result);
  }

  public Optional<User> getByUsernameAndPassword(String username, String password) {
    User result = null;
    try (Connection c = connection()) {
      PreparedStatement statement = c.prepareStatement("SELECT * FROM user u where u.username = ? and u.password = ?");
      statement.setString(1, username);
      statement.setString(2, password);
      statement.execute();
      ResultSet rs = statement.getResultSet();
      while (rs.next()) {
        result = createUser(rs);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return Optional.ofNullable(result);
  }

  @Override
  public boolean add(User value) {
    try (Connection connection = connection()) {
      PreparedStatement st = connection.prepareStatement("insert into user (username,password, lastseen, pp_link) values(?,?,?,?)");
      st.setString(1, value.getUsername());
      st.setString(2, value.getPassword());
      st.setTimestamp(3, Timestamp.valueOf(value.getLastSeen()));
      st.setString(4, value.getLink());
      return st.execute();
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public boolean delete(int id) {
    try (Connection c = connection()) {
      PreparedStatement st = c.prepareStatement("DELETE FROM user WHERE id = ?");
      st.setInt(1, id);
      return st.execute();
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  public void setLastSeen(int id) {
    try (Connection c = connection()) {
      PreparedStatement st = c.prepareStatement("UPDATE user SET lastseen = ? WHERE id = ?");
      st.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
      st.setInt(2, id);
      st.execute();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private User createUser(ResultSet rs) throws SQLException {
    return new User(rs.getInt("id"),
        rs.getString("username"),
        rs.getString("password"),
        rs.getTimestamp("lastseen") != null ? rs.getTimestamp("lastseen").toLocalDateTime() : null,
        rs.getString("pp_link"));
  }

}