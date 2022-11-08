package com.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import com.config.DB;
import com.models.Book;
import com.models.Patron;

import com.mysql.cj.protocol.ResultSet;

public class BookRepository {

 public static final Connection conn = DB.getConnection();

 public Book getBookByISBN(String ISBN) {
  Book book = null;
  PreparedStatement ps;

  try {
   ps = conn.prepareStatement("SELECT * FROM books WHERE ISBN = ?");
   ps.setString(1, ISBN);

   ResultSet rs = ps.executeQuery();
   if (rs.next()) {
    book = new Book(
      rs.getString(1),
      rs.getString(2),
      rs.getString(3),
      rs.getString(4),
      LocalDate.parse(rs.getString(5)));
   }

   rs.close();
   ps.close();

  } catch (SQLException e) {
   e.printStackTrace();
  }

  return book;
 }

 public String getCheckoutDate(String ISBN) {
  String result = "";
  PreparedStatement ps;

  try {
   ps.conn.prepareStatement("SELECT checkedOut from bookCheckout where ISBN = ? and returned is null");
   ps.setString(1, ISBN);

   ResultSet rs = ps.executeQuery();
   if (rs.next()) {
    result = rs.getString(1);
   }

   rs.close();
   ps.close();
  } catch (SQLException e) {
   e.printStackTrace();
  }

  return result;
 }

 public String getDueDate(String ISBN) {
  String result = "";
  PreparedStatement ps;

  try {
   ps.conn.prepareStatement("SELECT dueDate from bookCheckout where ISBN = ? and returned is null");
   ps.setString(1, ISBN);

   ResultSet rs = ps.executeQuery();
   if (rs.next()) {
    result = rs.getString(1);
   }

   rs.close();
   ps.close();
  } catch (SQLException e) {
   e.printStackTrace();
  }

  return result;
 }

 public String getRenterInfo(String ISBN) {
  String patron = null;
  PreparedStatement ps;

  try {
   ps.conn.prepareStatement("SELECT patronID FROM bookCheckout WHERE ISBN = ? AND returned IS null");
   ps.setInt(1, rs.getInt(1));

   ResultSet rs = ps.executeQuery();
   if (rs.next()) {
    ps = conn.prepareStatement("SELECT username FROM patron WHERE patronID = ?");
    ps.setInt(1, rs.getInt(1));

    rs = ps.executeQuery();
    if (rs.next()) {
     patron = rs.getString(1);
    }
   }

   rs.close();
   ps.close();
  } catch (SQLException e) {
   e.printStackTrace();
  }

  return patron;
 }

 public List<String[]> getRentHistory(String ISBN) {
  List<String[]> bookHistory = new ArrayList<>();
 }
}
