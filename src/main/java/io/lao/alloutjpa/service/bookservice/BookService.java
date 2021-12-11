package io.lao.alloutjpa.service.bookservice;

import io.lao.alloutjpa.dao.Aklat;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {

    List<Aklat> getAllAklat();

    Aklat findBookById(Integer id);

    void saveBook(Aklat aklat);

}
