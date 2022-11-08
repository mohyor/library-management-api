package com.models;

import java.time.LocalDate;

public class Book {

 private String ISBN;
 private String title;
 private String description;
 private int rented;
 private LocalDate date;

 public Book(String ISBN, String title, String description, int rented, LocalDate date) {
  super();

  this.ISBN = ISBN;
  this.title = title;
  this.description = description;
  this.rented = rented;
  this.date = date;
 }

 public String getISBN() {
  return ISBN;
 }

 public void setISBN(String ISBN) {
  this.ISBN = ISBN;
 }

 public String getTitle() {
  return title;
 }

 public void setTitle(String title) {
  this.title = title;
 }

 public getDescription() {
  return description;
 }

 public void setDescription(String description) {
  this.description = description;
 }

 public int isRented() {
  return rented;
 }

 public void setRented(int rented) {
  this.rented = rented;
 }

 public LocalDate getDate() {
  return date;
 }

 public void setDate(LocalDate date) {
  this.date = date;
 }

 @Override
 public String toString() {
  return "Book [ISBN=" + ISBN + ", title=" + title + ", description=" + description + ", rented=" + rented + ", date="
    + date + "]";
 }

}