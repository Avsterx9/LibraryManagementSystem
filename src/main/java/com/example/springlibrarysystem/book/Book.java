package com.example.springlibrarysystem.book;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "books")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long Id;

  private String title;

  private String author;

  private BookCategory bookCategory;

  private String publishingHouse;

  private LocalDate publishingDate;

  private String isbn;

  public Book(String title, String author, BookCategory bookCategory, String publishingHouse, LocalDate publishingDate, String isbn) {
    this.title = title;
    this.author = author;
    this.bookCategory = bookCategory;
    this.publishingHouse = publishingHouse;
    this.publishingDate = publishingDate;
    this.isbn = isbn;
  }
}
