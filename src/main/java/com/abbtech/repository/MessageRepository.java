package com.abbtech.repository;

import com.abbtech.domain.Message;
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

public class MessageRepository extends AbstractConnection implements DAO<Message> {

  @Override
  public List<Message> getAll() {
//        List<Message> messages = new ArrayList<>();
//        try (Connection c = connection()) {
//            Statement st = c.createStatement();
//            st.execute("SELECT * FROM messages");
//            ResultSet rs = st.getResultSet();
//            while (rs.next()) {
//                messages.add(createMessage(rs));
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return messages;
    return null;
  }

  public List<Message> getBySenderAndReceiverId(int senderId, int receiverId) {
    List<Message> messages = new ArrayList<>();
    try (Connection c = connection()) {
      String query = "SELECT * FROM message  WHERE user_from = ? AND user_to = ?";
      PreparedStatement st = c.prepareStatement(query);
      st.setInt(1, senderId);
      st.setInt(2, receiverId);
      st.execute();
      ResultSet rs1 = st.getResultSet();
      while (rs1.next()) {
        messages.add(createMessageWithoutId(rs1));
      }
      st.setInt(1, receiverId);
      st.setInt(2, senderId);
      st.execute();
      ResultSet rs2 = st.getResultSet();
      while (rs2.next()) {
        messages.add(createMessageWithoutId(rs2));
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    return messages;
  }

  @Override
  public Optional<Message> getById(int id) {
//        Message result = null;
//        try (Connection c = connection()) {
//            PreparedStatement st = c.prepareStatement("SELECT * FROM message where id = ?");
//            st.setInt(1, id);
//            st.execute();
//            ResultSet rs = st.getResultSet();
//            while (rs.next()) {
//                result = createMessage(rs);
//            }
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        return Optional.ofNullable(result);
    return Optional.empty();
  }

  @Override
  public boolean add(Message value) {
    try (Connection c = connection()) {
      PreparedStatement st = c.prepareStatement("INSERT into message(content, send_date, user_from, user_to) values (?,?,?,?)");
      st.setString(1, value.getMessage());
      st.setTimestamp(2, Timestamp.valueOf(value.getSendDate()));
      st.setInt(3, value.getFromUser());
      st.setInt(4, value.getToUser());
      return st.execute();
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public boolean delete(int id) {
//        try (Connection c = connection()) {
//
//            Statement st = c.createStatement();
//            return st.execute("DELETE FROM  message WHERE id = 1");
//        } catch (Exception e) {
//            return false;
//        }
    return false;
  }

  private Message createMessageWithoutId(ResultSet rs) throws SQLException {
    return new Message(
        rs.getString("content"),
        rs.getTimestamp("send_date"),
        rs.getInt("user_from"),
        rs.getInt("user_to")
    );
  }

//    private Message createMessageAllField(ResultSet rs) throws SQLException {
//        return new Message(
//                rs.getString("")
//                rs.getString("message"),
//                rs.getTimestamp("send_date")
//        );
//    }

}