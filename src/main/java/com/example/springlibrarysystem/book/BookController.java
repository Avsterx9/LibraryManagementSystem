package com.example.springlibrarysystem.book;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

  @PutMapping(path = "{id}")
  public void updateBook(@PathVariable("id") Long id,
                         @RequestParam(required = false) String title,
                         @RequestParam(required = false) String author,
                         @RequestParam(required = false) BookCategory bookCategory,
                         @RequestParam(required = false) String publishingHouse,
                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                         @RequestParam(required = false) String publishingDate,
                         @RequestParam(required = false) String isbn){
    System.out.println("gdfdlgklssfdjg" + publishingDate);
    bookService.updateBookDetails(id, title, author, bookCategory, publishingHouse, publishingDate, isbn);
  }


}
