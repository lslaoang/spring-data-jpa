package io.lao.alloutjpa.service.bookservice;

import io.lao.alloutjpa.model.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();

    Book findBookById(Integer id);

    void saveBook(Book book);

    long countBook();
}
