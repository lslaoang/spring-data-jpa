package io.lao.alloutjpa.controller;

import io.lao.alloutjpa.domain.MgaAklat;
import io.lao.alloutjpa.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping(value = {"","/books"})
    public MgaAklat findAll(){
        MgaAklat mgaAklat = new MgaAklat();
        mgaAklat.setMgaAklat(bookRepository.findAll());
        return mgaAklat;
    }
}
