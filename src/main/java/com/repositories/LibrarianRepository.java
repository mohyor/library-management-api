package com.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.models.Librarian;
import com.models.Patron;
import com.repositories.PatronRepository;

public class LibrarianRepository {

 public static final Connection conn = DB.getConnection();

 public boolean addBook(String ISBN, String title, String description) {

  PreparedStatement ps;

  try {

   ps = conn.prepareStatement("INSERT into book VALUES (?, ?, ?, 0, current_date())");

   ps.setString(1, ISBN);
   ps.setString(2, title);
   ps.setString(3, description);

   int res = ps.executeUpdate();

   ps.close();

   if (res != 0) {
    return true;
   }
  } catch (SQLException e) {
   e.printStackTrace();
  }

  return false;
 }

 public boolean deleteBook(String ISBN) {

  PreparedStatement ps;

  try {

   ps = conn.prepareStatement("DELETE FROM book WHERE ISBN = ?");

   ps.setString(1, ISBN);

   int res = ps.executeUpdate();

   ps.close();

   if (res != 0) {
    return true;
   }
  } catch (SQLException e) {
   e.printStackTrace();
  }

  return false;
 }

 public boolean updateBook(String ISBN, String title, String description) {

  PreparedStatement ps;

  try {

   ps = conn.prepareStatement("UPDATE book SET title = ?, description = ? WHERE ISBN = ?");

   ps.setString(1, title);
   ps.setString(2, description);
   ps.setString(3, ISBN);

   int res = ps.executeUpdate();

   ps.close();

   if (res != 0) {
    return true;
   }
  } catch (SQLException e) {
   e.printStackTrace();
  }

  return false;
 }

 public boolean unfreezeAccount(String username) {

  PreparedStatement ps = null;

  int res = 0;

  try {

   ps = conn.prepareStatement("UPDATE patron SET accountFrozen = 0 WHERE username = ?");

   ps.setString(1, username);

   res = ps.executeUpdate();

   ps.close();

  } catch (SQLException e) {
   e.printStackTrace();
  }

  if (res > 0) {
   return true;
  } else {
   return false;
  }
 }

 public boolean freezeAccount(String username) {

  PreparedStatement ps = null;

  int res = 0;

  try {

   ps = conn.prepareStatement("UPDATE patron SET accountFrozen = 1 WHERE username = ?");

   ps.setString(1, username);

   res = ps.executeUpdate();

   ps.close();

  } catch (SQLException e) {
   e.printStackTrace();
  }

  if (res > 0) {
   return true;
  } else {
   return false;
  }
 }

 public boolean updateUsername(String newUsername) {
  return false;
 }

 public boolean updatePassword(String newPassword) {
  return false;
 }

 public Librarian getLibrarianByUsername(String username) {

  Librarian librarian = null;

  try {

   PreparedStatement ps = conn.prepareStatement("SELECT * FROM librarian WHERE username = ?");

   ps.setString(1, username);

   ResultSet rs = ps.executeQuery();

   if (rs.next()) {

    librarian = new Librarian(
      rs.getInt(1),
      rs.getString(2),
      rs.getString(3));

    rs.close();

    ps.close();

    return librarian;
   }
  } catch (SQLException e) {
   e.printStackTrace();
  }

  return librarian;
 }

 public List<Patron> getAllPatrons() {

  List<Patron> allPatrons = new ArrayList<>();

  try {

   PreparedStatement ps = conn.prepareStatement("SELECT * FROM patron");

   ResultSet rs = ps.executeQuery();

   while (rs.next()) {

    Patron patron = new Patron(
      rs.getInt(1),
      rs.getString(2),
      rs.getString(3),
      rs.getString(4),
      rs.getString(5),
      rs.getString(6));

    allPatrons.add(patron);
   }

   rs.close();

   ps.close();

  } catch (SQLException e) {
   e.printStackTrace();
  }

  return allPatrons;
 }

 public List<Patron> getFrozenPatrons() {

  List<Patron> frozenPatrons = new ArrayList<>();

  try {

   PreparedStatement ps = conn.prepareStatement("SELECT * FROM patron WHERE accountFrozen = 1");

   ResultSet rs = ps.executeQuery();

   while (rs.next()) {

    Patron patron = new Patron(
      rs.getInt(1),
      rs.getString(2),
      rs.getString(3),
      rs.getString(4),
      rs.getString(5),
      rs.getInt(6));

    frozenPatrons.add(patron);
   }

   rs.close();

   ps.close();

  } catch (SQLException e) {
   e.printStackTrace();
  }

  return frozenPatrons;
 }

 public List<Patron> getActivePatrons() {

  List<Patron> activePatrons = new ArrayList<>();

  try {

   PreparedStatement ps = conn.prepareStatement("SELECT * FROM patron WHERE accountFrozen = 0");

   ResultSet rs = ps.executeQuery();

   while (rs.next()) {

    Patron patron = new Patron(
      rs.getInt(1),
      rs.getString(2),
      rs.getString(3),
      rs.getString(4),
      rs.getString(5),
      rs.getInt(6));

    activePatrons.add(patron);
   }

   rs.close();

   ps.close();

  } catch (SQLException e) {
   e.printStackTrace();
  }

  return activePatrons;
 }

 public void togglePatrons(String patron) {

  if (new PatronRepository()
    .getPatronByUsername(patron)
    .isFrozen() == 0) {
   freezeAccount(patron);
  } else {
   unfreezeAccount(patron);
  }
 }

}
