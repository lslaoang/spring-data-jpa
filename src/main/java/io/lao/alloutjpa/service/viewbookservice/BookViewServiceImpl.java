package io.lao.alloutjpa.service.viewbookservice;

import io.lao.alloutjpa.dao.Aklat;
import io.lao.alloutjpa.service.bookservice.BookService;
import io.lao.alloutjpa.view.BookView;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookViewServiceImpl implements  BookViewService{

    final BookService bookService;

    public BookViewServiceImpl(final BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public BookView viewBookById(Integer id) {
        return convertAklatToBookView(bookService.findBookById(id));
    }

    @Override
    public List<BookView> viewAllBook() {
        List<BookView> bookViewList = new ArrayList<>();
        bookService.getAllAklat().forEach(aklat -> bookViewList.add(convertAklatToBookView(aklat)));
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
