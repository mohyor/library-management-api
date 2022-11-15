package com.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import com.cognixia.jump.library.model.Patron;
import com.config.DB;
import com.models.Book;

public class PatronRepository {

  public static final Connection conn = DB.getConnection();

  public boolean checkoutBook(int patronID, String ISBN) {

    PreparedStatement ps = null;

    int res = 0;

    try {
      ps = conn.prepareStatement("INSERT into bookCheckout VALUES (null, ?, ?, ?, ?, null) ");

      ps.setInt(1, patronID);
      ps.setString(2, ISBN);
      ps.setString(3, LocalDate.now().toString());
      ps.setString(4, LocalDate.now().plusDays(7).toString());

      res = ps.executeUpdate();

      if (res > 0) {

        ps = conn.prepareStatement("UPDATE book SET rented = 1 WHERE ISBN = ?");
        ps.setString(1, ISBN);

        res = ps.executeUpdate();
      }

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

  public boolean returnBook(int patronID, String ISBN) {

    PreparedStatement ps = null;

    int res = 0;

    try {

      ps = conn.prepareStatement(
          "UPDATE bookCheckout SET RETURNED = current_date() WHERE ISBN = ? AND patronID = ? AND RETURNED is NULL");

      ps.setString(1, ISBN);
      ps.setInt(2, patronID);

      res = ps.executeUpdate();

      if (res > 0) {

        ps = conn.prepareStatement("UPDATE book SET RENTED = 0 WHERE ISBN = ?");

        ps.setString(1, ISBN);

        res = ps.executeUpdate();
      }

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

  public boolean updatePatron(int ID, String firstName, String lastName, String userName, String password) {

    PreparedStatement ps = null;

    int res = 0;

    try {

      ps = conn.prepareStatement("UPDATE patron SET firstName = ? WHERE patronID = ?");

      ps.setString(1, firstName);
      ps.setInt(2, ID);

      res += ps.executeUpdate();

      ps = conn.prepareStatement("UPDATE patron SET lastName = ? WHERE patronID = ?");

      ps.setString(1, userName);
      ps.setInt(2, ID);

      res += ps.executeUpdate();

      ps = conn.prepareStatement("UPDATE patron SET userName = ? WHERE patronID = ?");

      ps.setString(1, username);
      ps.setInt(2, ID);

      res += ps.executeUpdate();

      ps = conn.prepareStatement("UPDATE patron SET password = ? WHERE patronID = ?");

      ps.setString(1, password);
      ps.setInt(2, ID);

      res += ps.executeUpdate();

      ps.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    if (res == 4) {
      return true;
    } else {
      return false;
    }
  }

  public Patron getPatronByUsername(String username) {

    Patron patron = null;

    try {

      PreparedStatement ps = conn.prepareStatement("SELECT * FROM patron WHERE username = ?");

      ps.setString(1, username);

      ResultSet rs = ps.executeQuery();

      if (rs.next()) {

        patron = new Patron(
            rs.getInt(1),
            rs.getString(2),
            rs.getString(3),
            rs.getString(4),
            rs.getString(5),
            rs.getInt(6));

        rs.close();

        ps.close();

        return patron;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return patron;
  }

  public Patron getPatronByID(int ID) {

    Patron patron = null;

    try {

      PreparedStatement ps = conn.prepareStatement("SELECT * FROM patron WHERE patronID = ?");

      ps.setInt(1, ID);

      ResultSet rs = ps.executeQuery();

      if (rs.next()) {

        patron = new Patron(
            rs.getInt(1),
            rs.getString(2),
            rs.getString(3),
            rs.getString(4),
            rs.getString(5),
            rs.getInt(6));

        rs.close();

        ps.close();

        return patron;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return patron;
  }

  public List<Book> getAllBooks() {

    List<Book> allBooks = new ArrayList<>();

    try {

      PreparedStatement ps = conn.prepareStatement("SELECT * FROM book");

      ResultSet rs = ps.executeQuery();

      while (rs.next()) {

        Book book = new Book(
            rs.getString(1),
            rs.getString(2),
            rs.getString(3),
            rs.getInt(4),
            LocalDate.parse(rs.getString(5)));

        allBooks.add(book);
      }

      rs.close();

      ps.close();

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return allBooks;
  }

  public List<Book> getAvailableBooks() {

    List<Book> availableBooks = new ArrayList<>();

    try {

      PreparedStatement ps = conn.prepareStatement("SELECT * FROM book WHERE rented = 0");

      ResultSet rs = ps.executeQuery();

      while (rs.next()) {

        Book book = new Book(
            rs.getString(1),
            rs.getString(2),
            rs.getString(3),
            rs.getInt(4),
            LocalDate.parse(rs.getString(5)));

        availableBooks.add(book);
      }

      rs.close();

      ps.close();

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return availableBooks;
  }

  public List<Book> getRentedBooks(int userID) {

    List<Book> rentedBooks = new ArrayList<>();

    ResultSet rs2 = null;

    try {

      PreparedStatement ps = conn
          .prepareStatement("SELECT ISBN FROM bookCheckout WHERE patronID = ? AND RETURNED is NULL");

      ps.setInt(1, userID);

      ResultSet rs = ps.executeQuery();

      while (rs.next()) {

        ps = conn.prepareStatement("SELECT * FROM book WHERE ISBN = ?");

        ps.setString(1, rs.getString(1));

        rs2 = ps.executeQuery();

        rs2.next();

        Book book = new Book(
            rs2.getString(1),
            rs2.getString(2),
            rs2.getString(3),
            rs2.getInt(4),
            LocalDate.parse(rs2.getString(5)));

        rentedBooks.add(book);
      }

      rs.close();

      ps.close();

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return rentedBooks;
  }

  public List<BookCheckout> getRentalHistory(int patronID) {

    List<BookCheckout> history = new ArrayList<>();

    PreparedStatement ps = null;

    ResultSet rs = null;

    try {

      ps = conn.prepareStatement("SELECT * FROM bookCheckout WHERE patronID = ?");

      ps.setInt(1, patronID);

      rs = ps.executeQuery();

      while (rs.next()) {

        BookCheckout record = new BookCheckout(
            rs.getInt(1),
            rs.getInt(2),
            rs.getString(3),
            rs.getString(4),
            rs.getString(5),
            rs.getString(6));

        history.add(record);
      }

      rs.close();

      ps.close();

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return history;
  }
}
