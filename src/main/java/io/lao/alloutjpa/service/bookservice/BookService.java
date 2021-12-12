package io.lao.alloutjpa.service.bookservice;

import io.lao.alloutjpa.dao.Aklat;
import io.lao.alloutjpa.model.Books;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {

    List<Aklat> getAllAklat();

    Aklat findBookById(Integer id);

    void saveBook(Aklat aklat);

    void convertToAklatAndSave(Books books);

    long countBook();

}
