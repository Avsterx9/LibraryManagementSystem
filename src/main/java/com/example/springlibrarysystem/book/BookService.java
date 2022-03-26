package com.example.springlibrarysystem.book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ejb.Local;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookService {

  private final BookRepository bookRepository;

  @Autowired
  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  public List<Book> getAllBooks(){
    return bookRepository.findAll();
  }

  public void addBookToDB(Book book) {
    Optional<Book> bookOptional = bookRepository.findBookByIsbn(book.getIsbn());

    if(bookOptional.isPresent()){
      throw new IllegalStateException("Book with given ISBN already exists in database");
    }
    bookRepository.save(book);
  }

  public void deleteBook(Long id){
    boolean exists = bookRepository.existsById(id);

    if(!exists){
      throw new IllegalStateException(String.format("Cannot find book with %s", id));
    }
    bookRepository.deleteById(id);
  }

  @Transactional
  public void updateBookDetails(Long id, String title, String author, BookCategory bookCategory, String publishingHouse, String publishingDate, String isbn) {
    Book book = bookRepository.findById(id)
      .orElseThrow(() -> new IllegalStateException(String.format("Cannot find book with id %s", id)));

    if(title != null && title.length() > 0 && !Objects.equals(book.getTitle(), title)){
      book.setTitle(title);
    }

    if(author != null && author.length() > 0 && !Objects.equals(book.getAuthor(), author)){
      book.setAuthor(author);
    }

    if(bookCategory != null && !Objects.equals(book.getBookCategory(), bookCategory)){
      book.setBookCategory(bookCategory);
    }

    if(publishingHouse != null && publishingHouse.length() > 0 && !Objects.equals(book.getPublishingHouse(), publishingHouse)){
      book.setPublishingHouse(publishingHouse);
    }

    if(publishingDate != null && !Objects.equals(book.getPublishingDate().toString(), publishingDate)){
      book.setPublishingDate(LocalDate.parse(publishingDate));
    }

    if(author != null && author.length() > 0 && !Objects.equals(book.getAuthor(), author)){
      Optional<Book> bookOptional = bookRepository.findBookByIsbn(isbn);

      if(bookOptional.isPresent()){
        throw new IllegalStateException("Book with given ISBN already exists in database");
      }
      book.setIsbn(isbn);
    }
  }

}
