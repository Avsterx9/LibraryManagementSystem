package com.example.springlibrarysystem.book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
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
}
