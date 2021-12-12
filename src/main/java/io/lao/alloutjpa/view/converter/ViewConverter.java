package io.lao.alloutjpa.view.converter;

import io.lao.alloutjpa.model.Book;
import io.lao.alloutjpa.view.BookView;

public interface ViewConverter {

    Book viewToBook(BookView bookView);

    BookView bookToView(Book book);
}
