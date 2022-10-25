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

  public boolean login(String username, String password) {
    boolean result = false;
    try (Connection c = connection()) {
      PreparedStatement st = c.prepareStatement("SELECT * FROM user WHERE username = ? and  password = ?");
      st.setString(1, username);
      st.setString(2, password);
      ResultSet rs = st.executeQuery();
      if (rs.next() && rs.getString("username").equals(username)
          && rs.getString("password").equals(password)) {
        result = true;
      }

    } catch (Exception e) {
      result = false;
    }

    return result;
  }

  public String register(User user){
    try(Connection c = connection()) {
      PreparedStatement st = c.prepareStatement("INSERT INTO user(nickname,password,ppLink) VALUES (?,?,?)");
      st.setString(1,user.getUsername());
      st.setString(2,user.getPassword());
      st.setInt(3,user.getLink().getId());
      st.execute();
      return "Registration is succesfull";

    } catch (Exception e) {
      return "Registration is failed";
    }
  }
  public Optional<User> findByUsername(String username) {
    User result = null;
    try (Connection c = connection()) {
      PreparedStatement st = c.prepareStatement("SELECT *FROM user WHERE username = ?");
      st.setString(1, username);
      ResultSet rs = st.executeQuery();
      while (rs.next()) {
        result = createUser(rs);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return Optional.ofNullable(result);
  }

  public String updatePassword(User user) {
    try (Connection c = connection()) {
      PreparedStatement st = c.prepareStatement("UPDATE user SET password = ? WHERE id = ?");
      st.setString(1, user.getPassword());
      st.setString(2, user.getPassword());
      st.execute();
      return "Password could be changed succesfully";

    } catch (Exception e) {
      return "Password could not be changed";
    }

  }

  @Override
  public List<User> getAll() {
    List<User> users = new ArrayList<>();
    try (Connection c = connection()) {
      Statement statement = c.createStatement();
      statement.execute("SELECT u.id, u.username, u.idpp FROM users u");
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
      PreparedStatement statement = c.prepareStatement("SELECT u.id," + " u.username," + " u.idpp " + "FROM users u" + "where u.id = ?");
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
    try (Connection connection = connection()) {
      PreparedStatement preparedSt = connection.prepareStatement("insert into user (username,idpp, password) values(?,?,?)");
      preparedSt.setString(1, "u.username");
      preparedSt.setString(2, "u.idpp");
      preparedSt.setString(3,"password");
      return preparedSt.execute();
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public boolean delete(int id) {
    try (Connection c = connection()) {
      Statement stt = c.createStatement();
      return stt.execute("DELETE  FROM user WHERE id = 1");

    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  private User createUser(ResultSet rs) throws SQLException {
    return new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getInt("idpp"));
  }

}