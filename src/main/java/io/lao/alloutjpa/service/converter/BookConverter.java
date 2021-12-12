package io.lao.alloutjpa.service.converter;

import io.lao.alloutjpa.dao.JpaBook;
import io.lao.alloutjpa.model.Book;
import io.lao.alloutjpa.view.BookView;

public interface BookConverter {

    Book viewToBook(BookView bookView);

    BookView bookToView(Book book);

    JpaBook bookToJpaBook(Book book);

    Book jpaBookToBook(JpaBook jpaBook);
}
