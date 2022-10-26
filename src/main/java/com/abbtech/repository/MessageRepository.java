package com.abbtech.repository;

import com.abbtech.domain.Message;
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

public class MessageRepository extends AbstractConnection implements DAO<Message> {





  @Override
  public List<Message> getAll() {
    List<Message> messages = new ArrayList<>();
    try (Connection c = connection()) {
      Statement st = c.createStatement();
      st.execute("SELECT * FROM messages");
      ResultSet rs = st.getResultSet();
      while (rs.next()) {
        messages.add(createMessage(rs));
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return messages;
  }

  @Override
  public Optional<Message> getById(int id) {
    Message result = null;
    try (Connection c = connection()) {
      PreparedStatement st = c.prepareStatement("SELECT * FROM message where id = ?");
      st.setInt(1, id);
      st.execute();
      ResultSet rs = st.getResultSet();
      while (rs.next()) {
        result = createMessage(rs);
      }

    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    return Optional.ofNullable(result);
  }

  @Override
  public boolean add(Message value) {
    try (Connection c = connection()) {
      PreparedStatement st = connection()
          .prepareStatement("INSERT into message(message,fromUser,touser) values (?,?,?)");
      st.setString(1, "message");
      st.setString(2, "fromUser");
      st.setString(3, "toUser");

      return st.execute();

    } catch (Exception e) {
      e.printStackTrace();

      return false;
    }
  }

  @Override
  public boolean delete(int id) {
    try(Connection c = connection()){

    Statement st = c.createStatement();
    return st.execute("DELETE FROM  message WHERE id = 1");
    } catch (Exception e) {
      return false;
    }
  }

  private Message createMessage(ResultSet rs) throws SQLException {
    return new Message(rs.getInt("id"), rs.getString("message"),
        rs.getString("fromUser"), rs.getString("toUser"));
  }

}