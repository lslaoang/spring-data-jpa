package io.lao.alloutjpa.service.viewbookservice;

import io.lao.alloutjpa.dao.Aklat;
import io.lao.alloutjpa.repository.BookRepository;
import io.lao.alloutjpa.view.BookView;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookViewServiceImpl implements  BookViewService{

    final BookRepository bookRepository;
    final BookRepository bookService;

    public BookViewServiceImpl(BookRepository bookRepository, BookRepository bookService) {
        this.bookRepository = bookRepository;
        this.bookService = bookService;
    }

    @Override
    public BookView viewBookByid(Integer id) {
        return convertAklatToBook(bookService.getById(id));
    }

    @Override
    public List<BookView> viewAllBook() {
        return null;
    }

    private BookView convertAklatToBook(Aklat aklat){
        return new BookView(
                aklat.getId(),
                aklat.getName(),
                aklat.getGenre()
        );
    }
}
