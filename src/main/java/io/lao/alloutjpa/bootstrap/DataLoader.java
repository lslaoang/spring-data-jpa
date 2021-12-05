package io.lao.alloutjpa.bootstrap;

import io.lao.alloutjpa.dao.Aklat;
import io.lao.alloutjpa.dao.Genre;
import io.lao.alloutjpa.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    BookRepository bookRepository;


    @Override
    public void run(String... args) throws Exception {

        bookRepository.save(new Aklat(1,"Percy Jackson", Genre.SCI_FI));
        bookRepository.save(new Aklat(2,"The Alchemist", Genre.FICTION));
        bookRepository.save(new Aklat(3,"Geometry", Genre.EDUCATIONAL));
        bookRepository.save(new Aklat(4,"HEKASI", Genre.EDUCATIONAL));
    }
}
