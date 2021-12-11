package io.lao.alloutjpa.service.viewbookservice;

import io.lao.alloutjpa.dao.Aklat;
import io.lao.alloutjpa.service.bookservice.BookService;
import io.lao.alloutjpa.view.BookView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookViewServiceImpl implements  BookViewService{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(BookViewServiceImpl.class);
    final BookService bookService;

    public BookViewServiceImpl(final BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public BookView viewBookById(Integer id) {
        LOGGER.info("Retrieving book by ID");
        return convertAklatToBookView(bookService.findBookById(id));
    }

    @Override
    public List<BookView> viewAllBook() {
        LOGGER.info("Retrieving all books started.");
        List<BookView> bookViewList = new ArrayList<>();
        bookService.getAllAklat().forEach(aklat -> bookViewList.add(convertAklatToBookView(aklat)));
        LOGGER.info("Retrieving all books finished.");
        return bookViewList;
    }

    private BookView convertAklatToBookView(Aklat aklat){
        return new BookView(
                aklat.getId(),
                aklat.getName(),
                aklat.getGenre()
        );
    }
}
