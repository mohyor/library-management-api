package com.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.config.DB;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
 private static final long serialVersionUID = 1L;
 Connection conn;

 public void init(ServletConfig config) throws ServletException {
  conn = DB.getConnection();
 }

 public void destroy() {
 }

 protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
  String firstName = req.getParameter("firstName");
  String lastName = req.getParameter("lastName");
  String userName = req.getParameter("userName");
  String password = req.getParameter("password");

  PreparedStatement ps = null;
  ResultSet rs = null;

  try {
   ps = conn.prepareStatement('SELECT * FROM patron ')
  }
 }
}