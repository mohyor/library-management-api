package com.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {

 private static Connection conn;
 private static String DB_DRIVER = "com.mysql.cj.jdbc.Driver";

 private static String DB_HOST = "localhost";
 private static String DB_USER = "root";
 private static String DB_PASS = "Firebird14#";

 private DB() {
 }

 public static Connection getConnection() throws SQLException, ClassNotFoundException {

  Class.forName(DB_DRIVER);

  if ((conn == null) || connection.isClosed()) {
   connection = DriverManager.getConnection(DB_HOST, DB_USER, DB_PASS);
  }

  return conn;
 }
}