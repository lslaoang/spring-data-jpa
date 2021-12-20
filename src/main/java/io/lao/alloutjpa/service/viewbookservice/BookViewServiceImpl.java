package io.lao.alloutjpa.service.viewbookservice;

import io.lao.alloutjpa.service.bookservice.BookService;
import io.lao.alloutjpa.service.converter.BookConverter;
import io.lao.alloutjpa.view.BookView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("bookViewService")
public class BookViewServiceImpl implements BookViewService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookViewServiceImpl.class);
    final BookService bookService;
    final BookConverter bookConverter;

    public BookViewServiceImpl(final BookService bookService, BookConverter bookConverter) {
        this.bookService = bookService;
        this.bookConverter = bookConverter;
    }

    @Override
    public BookView viewBookById(String id) {
        LOGGER.info("Retrieving book by ID");
        BookView bookView = bookConverter.bookToView(bookService.findBookById(id));
        if (bookView != null) {
            return bookConverter.bookToView(bookService.findBookById(id));
        } else
            return null;
    }

    @Override
    public List<BookView> viewAllBook() {
        LOGGER.info("Retrieving all books started.");
        List<BookView> bookViewList = new ArrayList<>();
        bookService.getAllBooks().forEach(book -> {
            bookViewList.add(bookConverter.bookToView(book));
        });
        LOGGER.info("Retrieving all books finished.");
        return bookViewList;
    }

    @Override
    public void saveBookView(BookView bookView) {
        bookService.saveBook(bookConverter.viewToBook(bookView));
    }

}
