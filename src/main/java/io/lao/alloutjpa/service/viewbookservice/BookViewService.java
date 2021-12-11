package io.lao.alloutjpa.service.viewbookservice;

import io.lao.alloutjpa.view.BookView;

import java.util.List;

public interface BookViewService {

    BookView viewBookByid(Integer id);
    List<BookView> viewAllBook();
}
