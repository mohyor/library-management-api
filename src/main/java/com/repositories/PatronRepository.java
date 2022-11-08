package com.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import com.config.DB;

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
 }
}
