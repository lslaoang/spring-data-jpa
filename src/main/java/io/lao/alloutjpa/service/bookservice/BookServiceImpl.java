package io.lao.alloutjpa.service.bookservice;

import io.lao.alloutjpa.dao.Aklat;
import io.lao.alloutjpa.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BookServiceImpl implements BookService{

    @Autowired
    BookRepository bookRepository;

    @Override
    public List<Aklat> getAllAklat() {
        return bookRepository.findAll();
    }

    @Override
    public Aklat findBookById(Integer id) {
        return bookRepository.getById(id);
    }

    @Override
    public void saveBook(Aklat aklat) {
        bookRepository.save(aklat);
    }
}
