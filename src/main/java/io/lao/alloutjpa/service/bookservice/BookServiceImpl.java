package io.lao.alloutjpa.service.bookservice;

import io.lao.alloutjpa.model.Book;
import io.lao.alloutjpa.service.bookreposervice.BookRepoService;
import io.lao.alloutjpa.service.converter.BookConverter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    final BookRepoService bookRepoService;
    final BookConverter bookConverter;

    public BookServiceImpl(final BookRepoService bookRepoService, final BookConverter bookConverter) {
        this.bookRepoService = bookRepoService;
        this.bookConverter = bookConverter;
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> bookList = new ArrayList<>();
        bookRepoService.getAllBooks().forEach(jpaBook -> {
            bookList.add(bookConverter.jpaBookToBook(jpaBook));
        });
        return bookList;
    }

    @Override
    public Book findBookById(String id) {
        return bookConverter.jpaBookToBook(bookRepoService.findBookById(id));
    }

    @Override
    public void saveBook(Book book) {
        bookRepoService.saveBook(bookConverter.bookToJpaBook(book));
    }

    @Override
    public long countBook() {
        return bookRepoService.countBook();
    }
}
