package com.models;

public class Librarian {

 private int ID;
 private String username;
 private String password;

 public Librarian(int ID, String username, String password) {

  super();

  this.ID = ID;
  this.username = username;
  this.password = password;
 }

 public Librarian(String username, String password) {

  super();

  this.username = username;
  this.password = password;
 }

 public int getID() {
  return ID;
 }

 public void setID(int ID) {
  this.ID = ID;
 }

 public String getUsername() {
  return username;
 }

 public void setUsername(String username) {
  this.username = username;
 }

 public String getPassword() {
  return password;
 }

 public void setPassword(String password) {
  this.password = password;
 }

 @Override
 public String toString() {
  return "Librarian [ID=" + ID + ", username=" + username + ", password=" + password + "]";
 }
}
