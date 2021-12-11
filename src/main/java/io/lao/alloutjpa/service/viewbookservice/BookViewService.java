package io.lao.alloutjpa.service.viewbookservice;

import io.lao.alloutjpa.view.BookView;

import java.util.List;

public interface BookViewService {

    BookView viewBookById(Integer id);

    List<BookView> viewAllBook();
}
