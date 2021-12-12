package io.lao.alloutjpa.service.bookservice;

import io.lao.alloutjpa.dao.JpaBook;
import io.lao.alloutjpa.model.Books;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {

    List<JpaBook> getAllAklat();

    JpaBook findBookById(Integer id);

    void saveBook(JpaBook jpaBook);

    void convertToAklatAndSave(Books books);

    long countBook();

}
