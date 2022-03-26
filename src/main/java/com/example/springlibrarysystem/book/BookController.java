package com.example.springlibrarysystem.book;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/books")
public class BookController {

  private BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping
  public List<Book> getBooks(){
    return bookService.getAllBooks();
  }

  @PostMapping
  public void addNewBook(@RequestBody Book book){
    bookService.addBookToDB(book);
  }

  @DeleteMapping(path = "{id}")
  public void deleteBook(@PathVariable("id") Long id){
    bookService.deleteBook(id);
  }

}
