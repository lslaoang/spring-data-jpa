package io.lao.alloutjpa.service.viewbookservice;

import io.lao.alloutjpa.view.BookView;

import java.util.List;

public interface BookViewService {

    BookView viewBookById(String id);

    List<BookView> viewAllBook();

    void saveBookView(BookView bookView);
}
