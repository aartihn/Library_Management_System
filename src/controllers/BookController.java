package controllers;

import models.Book;
import services.BookService;

import java.util.List;

public class BookController {
    private final BookService bookService;

    public BookController() {
        this.bookService = new BookService();
    }

    public void addBook(Book book) {
        bookService.addBook(book);
    }

    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    public Book getBookById(int id) {
        return bookService.getBookById(id);
    }

    public void updateBook(Book book) {
        bookService.updateBook(book);
    }

    public void deleteBook(int id) {
        bookService.deleteBook(id);
    }
}
