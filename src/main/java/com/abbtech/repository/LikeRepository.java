package com.abbtech.repository;

import com.abbtech.domain.Like;
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

public class LikeRepository extends AbstractConnection implements DAO<Like> {

  @Override
  public List<Like> getAll() {
    List<Like> results = new ArrayList<>();
    try (Connection c = connection()) {
      String query = "SELECT * FROM like_table";
      Statement st = c.createStatement();
      st.execute(query);
      ResultSet rs = st.getResultSet();
      while (rs.next()) {
        results.add(createLike(rs));
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return results;
  }

  @Override
  public Optional<Like> getById(int id) {
    return Optional.empty();
  }

  public List<Like> getUserLikes(int fromId) {
    List<Like> results = new ArrayList<>();
    try (Connection c = connection()) {
      String query = "SELECT * FROM like_table WHERE fromid=?";
      PreparedStatement st = c.prepareStatement(query);
      st.setInt(1, fromId);
      st.execute();
      ResultSet rs = st.getResultSet();
      while (rs.next()) {
        results.add(createLike(rs));
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return results;
  }

  @Override
  public boolean add(Like value) {
    try (Connection c = connection()) {
      String query = "INSERT INTO like_table(fromid, toid) values (?, ?)";
      PreparedStatement st = c.prepareStatement(query);
      st.setInt(1, value.getFromWhom());
      st.setInt(2, value.getToWhom());
      return st.execute();
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  public boolean remove(Like value) {
    try (Connection c = connection()) {
      String query = "DELETE FROM like_table WHERE fromid = ? and toid = ?";
      PreparedStatement st = c.prepareStatement(query);
      st.setInt(1, value.getFromWhom());
      st.setInt(2, value.getToWhom());
      return st.execute();
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public boolean delete(int id) {
    try (Connection c = connection()) {
      String query = "DELETE FROM like_table WHERE fromid = ?";
      PreparedStatement st = c.prepareStatement(query);
      st.setInt(1, id);
      return st.execute();
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  private Like createLike(ResultSet rs) throws SQLException {
    return new Like(rs.getInt("id"), rs.getInt("fromid"), rs.getInt("toid"));
  }

}