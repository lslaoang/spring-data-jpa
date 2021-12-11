package io.lao.alloutjpa.service.bookservice;

import io.lao.alloutjpa.dao.Aklat;
import io.lao.alloutjpa.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    final BookRepository bookRepository;

    public BookServiceImpl(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

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

    @Override
    public long countBook() {
        return bookRepository.count();
    }
}
