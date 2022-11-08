package com.models;

public class Patron {

 private int ID;
 private String firstName;
 private String lastName;
 private String userName;
 private String password;
 private int frozen;

 public Patron(int ID, String firstName, String lastName, String userName, String password, int frozen) {
  super();

  this.ID = ID;
  this.firstName = firstName;
  this.lastName = lastName;
  this.userName = userName;
  this.password = password;
  this.frozen = frozen;
 }

 public int getID() {
  return ID;
 }

 public void setID(int ID) {
  this.ID = ID;
 }

 public String getFirstName() {
  return firstName;
 }

 public void setFirstName(String firstName) {
  this.firstName = firstName;
 }

 public String getLastName() {
  return lastName;
 }

 public String getUserName() {
  return username;
 }

 public void setUsername(String userName) {
  this.userName = userName;
 }

 public String getPassword() {
  return password;
 }

 public int isFrozen() {
  return frozen;
 }

 public void setFrozen(int frozen) {
  this.frozen = frozen;
 }

 @Override
 public String toString() {
  return "Patron [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName
    + ", password=" + password + ", frozen=" + frozen + "]";
 }
}