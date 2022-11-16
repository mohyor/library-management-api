package com.models;

public class BookCheckout {

 private int checkoutID;
 private int patronID;

 private String ISBN;
 private String checkoutDate;
 private String dueDate;
 private String returnedDate;

 public BookCheckout(int checkoutID, int patronID, String ISBN, String checkoutDate, String dueDate,
   String returnedDate) {

  super();

  this.checkoutID = checkoutID;
  this.patronID = patronID;
  this.ISBN = ISBN;
  this.checkoutDate = checkoutDate;
  this.dueDate = dueDate;
  this.returnedDate = returnedDate;
 }

 public int getCheckoutID() {
  return checkoutID;
 }

 public void setCheckoutID(int checkoutID) {
  this.checkoutID = checkoutID;
 }

 public int getPatronID() {
  return patronID;
 }

 public void setPatronID(int patronID) {
  this.patronID = patronID;
 }

 public String getISBN() {
  return ISBN;
 }

 public void setISBN(String ISBN) {
  this.ISBN = ISBN;
 }

 public String getCheckoutDate() {
  return checkoutDate;
 }

 public void setCheckoutDate(String checkoutDate) {
  this.checkoutDate = checkoutDate;
 }

 public String getDueDate() {
  return dueDate;
 }

 public void setDueDate(String dueDate) {
  this.dueDate = dueDate;
 }

 public String getReturnedDate() {

  String test = returnedDate + "";

  if (test.equals("null")) {
   return "---";
  }

  return returnedDate;
 }

 public void setReturnedDate(String returnedDate) {
  this.returnedDate = returnedDate;
 }

 @Override
 public String toString() {
  return "BookCheckout [checkoutID=" + checkoutID + ", patronID=" + patronID + ", ISBN=" + ISBN
    + ", checkoutDate=" + checkoutDate + ", dueDate=" + dueDate + ", returnedDate=" + returnedDate + "]";
 }
}