package io.lao.alloutjpa.service.viewbookservice;

import io.lao.alloutjpa.dao.Genre;
import io.lao.alloutjpa.model.Book;
import io.lao.alloutjpa.service.converter.BookConverter;
import io.lao.alloutjpa.view.BookView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookViewServiceTest {

    @Mock
    private BookConverter bookConverter;

    @InjectMocks
    private BookViewServiceImpl bookViewService;

    Book getDefaultBook() {
        return new Book("101", "ThisBook", Genre.THRILLER);
    }

    BookView getDefaultBookView() {
        return new BookView("101", "ThisBook", Genre.THRILLER);
    }

    @Test
    public void shouldReturnBookView() {
        Book book = getDefaultBook();
        BookView bookview = getDefaultBookView();
        when(bookViewService.viewBookById(any())).thenReturn(bookview);
        BookView bookView1 = bookViewService.viewBookById(book.getId());
        assertThat(bookView1).isEqualTo(bookview);
    }

    @Test
    public void shouldConvertViewToBookToView() {
        BookView bookView = getDefaultBookView();
        Book book = getDefaultBook();
        when(bookConverter.viewToBook(any())).thenReturn(book);
        when(bookConverter.bookToView(any())).thenReturn(bookView);

        Book book1 = bookConverter.viewToBook(bookView);
        BookView bookView1 = bookConverter.bookToView(book);

        assertThat(book1.getId()).isEqualTo(book.getId());
        assertThat(bookView1.getGenre()).isEqualTo(book.getGenre());

    }
}
