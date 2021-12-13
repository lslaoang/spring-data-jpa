package io.lao.alloutjpa.service.bookreposervice;

import io.lao.alloutjpa.dao.JpaBook;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookRepoService {

    List<JpaBook> getAllAklat();

    JpaBook findBookById(Integer id);

    void saveBook(JpaBook jpaBook);

    long countBook();

}
